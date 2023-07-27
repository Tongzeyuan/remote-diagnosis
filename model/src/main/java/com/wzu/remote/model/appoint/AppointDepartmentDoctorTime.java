package com.wzu.remote.model.appoint;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "可用时间")
@TableName("appoint_department_doctor_time")
public class AppointDepartmentDoctorTime {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id(医生)")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "科室id")
    @TableField("department_id")
    private Long departmentId;

    @ApiModelProperty(value = "起始时间")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "可预约数量")
    @TableField("reservation_count")
    private Integer reservationCount;

    @ApiModelProperty(value = "预约价格")
    @TableField("price")
    private Double price;

}
