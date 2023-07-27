package com.wzu.remote.model.vo.appoint;


import lombok.Data;

import java.util.Date;


/**
 * 预约 选择就诊日期
 */
@Data
public class BigTimeVo {

    /**
     * 年-月-日
     */
    private String date;

    /**
     * 星期
     */
    private String week;
}
