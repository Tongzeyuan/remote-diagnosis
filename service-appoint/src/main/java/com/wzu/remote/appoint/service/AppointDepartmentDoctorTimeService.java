package com.wzu.remote.appoint.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzu.remote.model.appoint.AppointDepartmentDoctorTime;
import com.wzu.remote.model.appoint.AppointDoctorInfo;
import com.wzu.remote.model.appoint.AppointPatientInfo;
import com.wzu.remote.model.vo.appoint.BigTimeVo;
import com.wzu.remote.model.vo.appoint.DetailTimeQueryVo;
import com.wzu.remote.model.vo.appoint.DoctorQueryVo;

import java.util.List;
import java.util.Map;

public interface AppointDepartmentDoctorTimeService extends IService<AppointDepartmentDoctorTime> {
    List<BigTimeVo> listBigTime(Long departmentId);

    IPage<AppointDoctorInfo> listDoctor(DoctorQueryVo queryDoctorVo, Page<AppointDoctorInfo> pageParam);

    Map<String, Object> listDetailTime(DetailTimeQueryVo detailTimeQueryVo);
}
