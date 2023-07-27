package com.wzu.remote.appoint.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzu.remote.appoint.mapper.AppointDepartmentMapper;
import com.wzu.remote.appoint.service.AppointDepartmentService;
import com.wzu.remote.model.appoint.AppointDepartment;
import org.springframework.stereotype.Service;

@Service
public class AppointDepartmentServiceImpl extends ServiceImpl<AppointDepartmentMapper, AppointDepartment> implements AppointDepartmentService {
}
