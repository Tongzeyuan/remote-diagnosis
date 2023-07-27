package com.wzu.remote.model.vo.system;


import lombok.Data;

/**
 * 登录对象
 */
@Data
public class LoginVo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
