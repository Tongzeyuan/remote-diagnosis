package com.wzu.remote.appoint.controller;


import com.wzu.remote.appoint.service.AppointPatientInfoService;
import com.wzu.remote.common.result.Result;
import com.wzu.remote.model.appoint.AppointPatientInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "病人信息管理")
@RestController
@RequestMapping(value = "/appoint/patient_info")
public class AppointPatientInfoController {

    @Autowired
    private AppointPatientInfoService appointPatientInfoService;

    @ApiOperation(value = "查看病人信息")
    @GetMapping("/{userId}")
    public Result getPatientInfo(@PathVariable(value = "userId") Long userId){
        AppointPatientInfo patientInfo = appointPatientInfoService.getById(userId);
        if(patientInfo == null){
            return Result.fail().message("查找失败");
        }
        return Result.ok(patientInfo).message("查找成功");
    }

    @ApiOperation(value = "修改病人信息")
    @PutMapping("/")
    public Result updatePatientInfo(@RequestBody AppointPatientInfo appointPatientInfo){
        Boolean bool = appointPatientInfoService.updatePatientInfo(appointPatientInfo);
        if(bool = false) return Result.fail().message("修改失败");
        return Result.ok().message("修改病人信息成功");
    }

}
