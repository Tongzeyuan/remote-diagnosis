package com.wzu.remote.appoint.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzu.remote.appoint.service.AppointDepartmentDoctorTimeService;
import com.wzu.remote.common.result.Result;
import com.wzu.remote.model.appoint.AppointDepartmentDoctorTime;
import com.wzu.remote.model.appoint.AppointDoctorInfo;
import com.wzu.remote.model.vo.appoint.BigTimeVo;
import com.wzu.remote.model.vo.appoint.DetailTimeQueryVo;
import com.wzu.remote.model.vo.appoint.DoctorQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "可用时间管理")
@RestController
@RequestMapping(value = "/appoint/department_doctor_time")
public class AppointDepartmentDoctorTimeController {

    @Autowired
    private AppointDepartmentDoctorTimeService departmentDoctorTimeService;

    @ApiOperation(value = "添加可用时间")
    @PostMapping("/")
    public Result saveDepartmentDoctorTime(@RequestBody AppointDepartmentDoctorTime departmentDoctorTime){
        boolean bool = departmentDoctorTimeService.save(departmentDoctorTime);
        if(bool){
            return Result.ok().message("添加成功");
        }else{
            return Result.fail().message("添加失败");
        }
    }

    @ApiOperation(value = "根据科室id查看可预约的日期(年,月,日,星期)")
    @GetMapping("/big_time/{departmentId}")
    public Result listBigTime(@PathVariable(value = "departmentId") Long departmentId){
        List<BigTimeVo> bigTimeList = departmentDoctorTimeService.listBigTime(departmentId);
        return Result.ok(bigTimeList);
    }

    @ApiOperation(value = "根据科室id和预约日期分页查看就诊医生(医生姓名,头像,职称)")
    @GetMapping("/doctor/{limit}/{page}")
    public Result listDoctor(DoctorQueryVo doctorQueryVo,
                             @PathVariable(value = "limit") Long limit,
                             @PathVariable(value = "page") Long page){
        Page<AppointDoctorInfo> pageParam = new Page<>(page,limit);
        IPage<AppointDoctorInfo> pageModel = departmentDoctorTimeService.listDoctor(doctorQueryVo,pageParam);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "根据科室id,预约日期和医生id查看医生信息(全)和具体日期(上午 8:00-8:10 预约数量)")
    @GetMapping("/detail_time")
    public Result listDetailTime(DetailTimeQueryVo detailTimeQueryVo){
        Map<String,Object> infoMap = departmentDoctorTimeService.listDetailTime(detailTimeQueryVo);
        return Result.ok(infoMap);
    }
}
