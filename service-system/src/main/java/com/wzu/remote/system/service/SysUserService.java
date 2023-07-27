package com.wzu.remote.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzu.remote.model.system.SysUser;

public interface SysUserService extends IService<SysUser> {

    //根据用户名获取用户信息
    SysUser getByUserName(String userName);
}
