package com.wzu.remote.appoint.controller;


import com.wzu.remote.appoint.service.AppointReservationService;
import com.wzu.remote.common.result.Result;
import com.wzu.remote.model.appoint.AppointReservation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "预约管理")
@RestController
@RequestMapping(value = "/appoint/reservation")
public class AppointReservationController {

    @Autowired
    private AppointReservationService appointReservationService;

    @ApiOperation(value = "查看可用的预约")
    @GetMapping("/{userId}")
    public Result getAvailableReservation(@PathVariable(value = "userId") Long userId){
        AppointReservation appointReservation = appointReservationService.getAvailableReservation(userId);
        if(appointReservation == null){
            return Result.fail().message("没有可用的预约");
        }
        return Result.ok(appointReservation);
    }
}
