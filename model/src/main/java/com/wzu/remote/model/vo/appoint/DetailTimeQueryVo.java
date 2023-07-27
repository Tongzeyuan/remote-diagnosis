package com.wzu.remote.model.vo.appoint;


import lombok.Data;

/**
 * 预约 选择就诊医生的详细时间段
 */
@Data
public class DetailTimeQueryVo {

    /**
     * 科室id
     */
    private Long departmentId;

    /**
     * 年-月-日
     */
    private String date;

    /**
     * 医生id
     */
    private Long userId;
}
