package com.wzu.remote.model.appoint;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wzu.remote.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "病人信息")
@TableName("appoint_patient_info")
public class AppointPatientInfo extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty(value = "性别(0:男,1:女)")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "电话号码")
    @TableField("phone_number")
    private String phoneNumber;
}
