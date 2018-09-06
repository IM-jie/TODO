package com.demo.service.iservice;

/**
 * @program: parent
 * @description:发送邮件接口类
 * @author: zouweidong
 * @create: 2018-08-31 11:40
 **/
public interface ISendMailSV {
    void sendSimpleMail(String to, String subject, String content);
    void sendHtmlMail(String to, String subject, String content);
    void sendAttachmentsMail(String to, String subject,String content, String filePath);
}
