package com.wzu.remote.model.vo.appoint;


import lombok.Data;

/**
 * 发送给前端的数据格式
 * 具体日期(上午 8:00-8:10 预约数量)
 */
@Data
public class DetailTimeVo {

    /**
     * 上下午
     */
    private String morning;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 可预约数量
     */
    private Integer count;

}
