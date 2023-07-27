package com.wzu.remote.appoint.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzu.remote.model.appoint.AppointPatientInfo;

public interface AppointPatientInfoService extends IService<AppointPatientInfo> {

    Boolean updatePatientInfo(AppointPatientInfo appointPatientInfo);
}
