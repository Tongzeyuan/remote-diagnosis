package com.wzu.remote.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzu.remote.model.system.SysUser;
import com.wzu.remote.system.mapper.SysUserMapper;
import com.wzu.remote.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUserName(String userName) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName,userName);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        return sysUser;
    }
}
