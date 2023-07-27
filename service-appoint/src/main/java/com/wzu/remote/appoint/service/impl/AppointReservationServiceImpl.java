package com.wzu.remote.appoint.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzu.remote.appoint.mapper.AppointDepartmentDoctorTimeMapper;
import com.wzu.remote.appoint.mapper.AppointReservationMapper;
import com.wzu.remote.appoint.service.AppointReservationService;
import com.wzu.remote.model.appoint.AppointDepartmentDoctorTime;
import com.wzu.remote.model.appoint.AppointReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AppointReservationServiceImpl extends ServiceImpl<AppointReservationMapper, AppointReservation> implements AppointReservationService {

    @Autowired
    private AppointDepartmentDoctorTimeMapper appointDepartmentDoctorTimeMapper;

    @Override
    public AppointReservation getAvailableReservation(Long userId) {
        LambdaQueryWrapper<AppointReservation> wrapper = new LambdaQueryWrapper<>();
        //查找某个病人最新的可用的预约
        wrapper.eq(AppointReservation::getUserId,userId)
                .eq(AppointReservation::getState,1) //已支付且未签到
                .orderByAsc(AppointReservation::getCreateTime);//按预约创建时间倒序排(最前面最新创建)
        AppointReservation reservation = this.getOne(wrapper);
        //如果存在最新可用预约
        if(reservation != null){
            //判断此时是否过了预约的可用时间的结束时间(>=结束时间)
            Long departmentDoctorTimeId = reservation.getDepartmentDoctorTimeId();//可用时间id
            AppointDepartmentDoctorTime departmentDoctorTime = appointDepartmentDoctorTimeMapper.selectById(departmentDoctorTimeId);//可用时间
            //如果过了,则将该预约的state置2(已作废),不返回预约信息
            if(new Date().compareTo(departmentDoctorTime.getEndTime()) >= 0  ){
                reservation.setState(2);
                reservation = null;
            }
        }
        return reservation;
    }
}
