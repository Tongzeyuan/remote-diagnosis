package com.wzu.remote.model.appoint;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wzu.remote.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "预约")
@TableName("appoint_reservation")
public class AppointReservation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id(病人)")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "可用时间id")
    @TableField("department_doctor_time_id")
    private Long departmentDoctorTimeId;

    @ApiModelProperty(value = "支付状态(0:未支付,1:已支付)")
    @TableField("state")
    private Integer state;

    @ApiModelProperty(value = "amount")
    @TableField("amount")
    private Double amount;
}
