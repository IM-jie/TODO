package com.demo.entity.param;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @program: parent
 * @description: 用户修改校验类
 * @author: zouweidong
 * @create: 2018-08-31 15:47
 **/
public class AdminUserUpdateParam implements Serializable {

    /**
     * 用户邮箱
     */
    @NotEmpty(message = "用户邮箱不能为空")
    private String mail;

    /**
     * 手机
     */
    @NotEmpty(message = "手机不能为空")
    private String phone;

    /**
     * 分机
     */
    private String phoneCopy;

    /**
     * 工号
     */
    private String empNo;

    /**
     * 微博名称
     */
    private String blogName;

    /**
     * 备注
     */
    private String remark;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCopy() {
        return phoneCopy;
    }

    public void setPhoneCopy(String phoneCopy) {
        this.phoneCopy = phoneCopy;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
