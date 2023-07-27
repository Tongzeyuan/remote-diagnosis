package com.wzu.remote.appoint.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzu.remote.appoint.mapper.AppointPatientInfoMapper;
import com.wzu.remote.appoint.service.AppointPatientInfoService;
import com.wzu.remote.model.appoint.AppointPatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointPatientInfoServiceImpl  extends ServiceImpl<AppointPatientInfoMapper, AppointPatientInfo> implements AppointPatientInfoService {

    @Override
    public Boolean updatePatientInfo(AppointPatientInfo appointPatientInfo) {
        LambdaQueryWrapper<AppointPatientInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AppointPatientInfo::getUserId,appointPatientInfo.getUserId());
        boolean bool = this.update(appointPatientInfo, wrapper);
        return bool;
    }
}
