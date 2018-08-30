package com.demo.entity.param;


import javax.validation.constraints.NotEmpty;

/**
 * @program: parent
 * @description: 用户添加校验类
 * @author: zouweidong
 * @create: 2018-08-30 15:26
 **/
public class AdminUserAddParam {

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 用户邮箱
     */
    @NotEmpty(message = "手机号码不能为空")
    private String mail;

    /**
     * 密码
     */
    @NotEmpty(message = "用户昵称不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
