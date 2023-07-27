package com.wzu.remote.system.controller;


import com.wzu.remote.common.config.exception.CustomException;
import com.wzu.remote.common.jwt.JwtHelper;
import com.wzu.remote.common.result.Result;
import com.wzu.remote.common.utils.MD5;
import com.wzu.remote.model.system.SysUser;
import com.wzu.remote.model.vo.system.LoginVo;
import com.wzu.remote.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录管理")
@RestController
@RequestMapping(value = "/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录,成功则返回token
     * @param loginVo
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping ("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        SysUser sysUser = sysUserService.getByUserName(loginVo.getUserName());
        if (sysUser == null) {
            throw new CustomException(201, "用户不存在");
        }
        if (sysUser.getPassword().equals(loginVo.getPassword()) == false) {
            throw new CustomException(201, "密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUserName()));
        return Result.ok(map);
    }
}
