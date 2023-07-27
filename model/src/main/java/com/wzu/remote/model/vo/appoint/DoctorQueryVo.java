package com.wzu.remote.model.vo.appoint;


import lombok.Data;

/**
 * 预约 选择就诊医生
 */
@Data
public class DoctorQueryVo {

    /**
     * 科室id
     */
    private Long departmentId;

    /**
     * 年-月-日
     */
    private String date;
}
