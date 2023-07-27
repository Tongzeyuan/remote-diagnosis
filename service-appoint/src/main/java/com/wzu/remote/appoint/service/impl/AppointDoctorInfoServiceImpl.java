package com.wzu.remote.appoint.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzu.remote.appoint.mapper.AppointDoctorInfoMapper;
import com.wzu.remote.appoint.service.AppointDoctorInfoService;
import com.wzu.remote.model.appoint.AppointDoctorInfo;
import org.springframework.stereotype.Service;

@Service
public class AppointDoctorInfoServiceImpl extends ServiceImpl<AppointDoctorInfoMapper, AppointDoctorInfo> implements AppointDoctorInfoService {
}
