package com.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @program: parent
 * @description: 密码加密解密工具类
 * @author: zouweidong
 * @create: 2018-08-28 11:49
 **/
public class PasswordCryptoUtil {

    static Logger logger = LoggerFactory.getLogger(PasswordCryptoUtil.class);
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decode(String key) {
        byte[] bt;
        try {
            bt = (new BASE64Decoder()).decodeBuffer(key);
            //如果出现乱码可以改成： String(bt, "utf-8")或 gbk
            return new String(bt, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.info("解密错误"+e);
            return "";
        }
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encode(String key) {
        byte[] bt = key.getBytes();
        return (new BASE64Encoder()).encodeBuffer(bt);
    }
}
