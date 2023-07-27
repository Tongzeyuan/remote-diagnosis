package com.wzu.remote.appoint.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzu.remote.appoint.service.AppointDepartmentService;
import com.wzu.remote.common.result.Result;
import com.wzu.remote.model.appoint.AppointDepartment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "科室管理")
@RestController
@RequestMapping(value = "/appoint/department")
public class AppointDepartmentController {

    @Autowired
    private AppointDepartmentService appointDepartmentService;

    @ApiOperation(value = "分页查看科室")
    @GetMapping("/{limit}/{page}")
    public Result pageDepartment(
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable(value = "limit") Long limit,
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable(value = "page") Long page){
        Page<AppointDepartment> pageParam = new Page<>(page,limit);
        IPage<AppointDepartment> pageModel = appointDepartmentService.page(pageParam);
        return Result.ok(pageModel);
    }
}
