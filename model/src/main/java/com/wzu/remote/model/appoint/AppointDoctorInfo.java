package com.wzu.remote.model.appoint;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wzu.remote.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "医生信息")
@TableName("appoint_doctor_info")
public class AppointDoctorInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "科室id")
    @TableField("department_id")
    private Long departmentId;

    @ApiModelProperty(value = "真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("职称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "介绍")
    @TableField("description")
    private String description;
}
