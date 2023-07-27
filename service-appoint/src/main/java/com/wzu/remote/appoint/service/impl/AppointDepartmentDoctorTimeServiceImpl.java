package com.wzu.remote.appoint.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzu.remote.appoint.mapper.AppointDepartmentDoctorTimeMapper;
import com.wzu.remote.appoint.mapper.AppointDoctorInfoMapper;
import com.wzu.remote.appoint.service.AppointDepartmentDoctorTimeService;
import com.wzu.remote.model.appoint.AppointDepartmentDoctorTime;
import com.wzu.remote.model.appoint.AppointDoctorInfo;
import com.wzu.remote.model.vo.appoint.BigTimeVo;
import com.wzu.remote.model.vo.appoint.DetailTimeQueryVo;
import com.wzu.remote.model.vo.appoint.DetailTimeVo;
import com.wzu.remote.model.vo.appoint.DoctorQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointDepartmentDoctorTimeServiceImpl extends ServiceImpl<AppointDepartmentDoctorTimeMapper, AppointDepartmentDoctorTime> implements AppointDepartmentDoctorTimeService {

    @Autowired
    private AppointDepartmentDoctorTimeMapper departmentDoctorTimeMapper;

    @Autowired
    private AppointDoctorInfoMapper appointDoctorInfoMapper;

    /**
     * 根据科室id查看可预约的日期(年,月,日,星期)
     * @param departmentId
     * @return
     */
    @Override
    public List<BigTimeVo> listBigTime(Long departmentId) {
        //1.先根据科室id获取指定可用时间
        LambdaQueryWrapper<AppointDepartmentDoctorTime> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AppointDepartmentDoctorTime::getDepartmentId,departmentId);
        List<AppointDepartmentDoctorTime> appointDepartmentDoctorTimeList = this.list(wrapper);
        //2.获取字段start_time的年-月-日(不能重复)
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        List<BigTimeVo> bigTimeVoList = appointDepartmentDoctorTimeList.stream().map(a ->
        {
            String date = ft.format(a.getStartTime());
            String week = getWeekOfDate(a.getStartTime());
            BigTimeVo bigTimeVo = new BigTimeVo();
            bigTimeVo.setDate(date);
            bigTimeVo.setWeek(week);
            return bigTimeVo;
        }).distinct().collect(Collectors.toList());
        return bigTimeVoList;
    }

    /**
     * 根据科室id和预约日期分页查看就诊医生(医生姓名,头像,职称)
     * @param doctorQueryVo
     * @param pageParam
     * @return
     */
    @Override
    public IPage<AppointDoctorInfo> listDoctor(DoctorQueryVo doctorQueryVo,Page<AppointDoctorInfo> pageParam) {
        String date = doctorQueryVo.getDate();//年-月-日
        String dateSmall = date + " 00:00:00";
        String dateBig = date + " 23:59:59";
        Long departmentId = doctorQueryVo.getDepartmentId();//科室id
        QueryWrapper<AppointDepartmentDoctorTime> wrapper = new QueryWrapper<>();
        //user_id(医生id)不能重复
        wrapper.select("DISTINCT user_id")
                .lambda()
                .eq(AppointDepartmentDoctorTime::getDepartmentId,departmentId)
                .ge(AppointDepartmentDoctorTime::getStartTime,dateSmall)
                .le(AppointDepartmentDoctorTime::getStartTime,dateBig);
        Page<AppointDepartmentDoctorTime> appointDepartmentDoctorTimePage = this.page(new Page<AppointDepartmentDoctorTime>(pageParam.getCurrent(), pageParam.getSize()), wrapper);

        List<AppointDoctorInfo> doctorInfoList = appointDepartmentDoctorTimePage.getRecords().stream().map(a -> {
            Long userId = a.getUserId();
            //根据userId获取医生信息
            LambdaQueryWrapper<AppointDoctorInfo> doctorInfoWrapper = new LambdaQueryWrapper<>();
            doctorInfoWrapper.eq(AppointDoctorInfo::getUserId, userId);
            return appointDoctorInfoMapper.selectOne(doctorInfoWrapper);
        }).collect(Collectors.toList());

        IPage<AppointDoctorInfo> pageModel = pageParam.setCurrent(appointDepartmentDoctorTimePage.getCurrent())
                .setSize(appointDepartmentDoctorTimePage.getSize())
                .setTotal(appointDepartmentDoctorTimePage.getTotal())
                .setRecords(doctorInfoList);
        return pageModel;
    }

    /**
     * 根据科室id,预约日期和医生id查看医生信息(全)和具体日期(上午 8:00-8:10 预约数量)
     * @param detailTimeQueryVo
     * @return
     */
    @Override
    public Map<String, Object> listDetailTime(DetailTimeQueryVo detailTimeQueryVo) {
        String date = detailTimeQueryVo.getDate();//年-月-日
        String dateSmall = date + " 00:00:00";
        String dateBig = date + " 23:59:59";
        LambdaQueryWrapper<AppointDepartmentDoctorTime> wrapper = new LambdaQueryWrapper();
        wrapper.eq(AppointDepartmentDoctorTime::getDepartmentId,detailTimeQueryVo.getDepartmentId())
                .ge(AppointDepartmentDoctorTime::getStartTime,dateSmall)
                .le(AppointDepartmentDoctorTime::getStartTime,dateBig)
                .eq(AppointDepartmentDoctorTime::getUserId,detailTimeQueryVo.getUserId());
        List<AppointDepartmentDoctorTime> list = this.list(wrapper);
        //获取详细时间列表
        List<DetailTimeVo> detailTimeVos = list.stream().map(a -> {
            DetailTimeVo detailTimeVo = new DetailTimeVo();
            //填充可预约数量
            detailTimeVo.setCount(a.getReservationCount());
            //获取原始 起始,结束时间
            Date startTime = a.getStartTime();
            Date endTime = a.getEndTime();
            //起始,结束时间截取时分
            SimpleDateFormat df = new SimpleDateFormat("h:mm a");
            String startTimeFt = df.format(startTime).split(" ")[0];
            String morning = df.format(startTime).split(" ")[1];
            String endTimeFt = df.format(endTime).split(" ")[0];
            //填充起始时间,结束时间,上下午
            detailTimeVo.setStartTime(startTimeFt);
            detailTimeVo.setEndTime(endTimeFt);
            detailTimeVo.setMorning(morning);
            return detailTimeVo;
        }).collect(Collectors.toList());
        //获取医生信息
        AppointDoctorInfo doctorInfo = getDoctorInfoByUserId(detailTimeQueryVo.getUserId());
        HashMap<String,Object> map = new HashMap<>();
        map.put("doctorInfo",doctorInfo);
        map.put("detailTimeList",detailTimeVos);
        return map;
    }

    /**
     * 获取当前日期是周几
     * @param date
     * @return 当前日期是周几
     */
    public String getWeekOfDate(Date date) {
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 根据用户id(医生id)获取医生信息
     * @param userId
     * @return
     */
    public AppointDoctorInfo getDoctorInfoByUserId(Long userId){
        LambdaQueryWrapper<AppointDoctorInfo> doctorInfoWrapper = new LambdaQueryWrapper<>();
        doctorInfoWrapper.eq(AppointDoctorInfo::getUserId, userId);
        return appointDoctorInfoMapper.selectOne(doctorInfoWrapper);
    }
}
