package com.demo.entity;

import java.util.Date;

public class AdminUser {
    private Integer id;

    private String userid;

    private String username;

    private String pinyin;

    private String mail;

    private String phone;

    private String phoneCopy;

    private String number;

    private String blogname;

    private String remark;

    private String password;

    private String salt;

    private Byte permissionid;

    private String group;

    private String creater;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoneCopy() {
        return phoneCopy;
    }

    public void setPhoneCopy(String phoneCopy) {
        this.phoneCopy = phoneCopy == null ? null : phoneCopy.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname == null ? null : blogname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Byte getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Byte permissionid) {
        this.permissionid = permissionid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", mail=").append(mail);
        sb.append(", phone=").append(phone);
        sb.append(", phoneCopy=").append(phoneCopy);
        sb.append(", number=").append(number);
        sb.append(", blogname=").append(blogname);
        sb.append(", remark=").append(remark);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", permissionid=").append(permissionid);
        sb.append(", group=").append(group);
        sb.append(", creater=").append(creater);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}