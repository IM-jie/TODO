package com.demo.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: chopmkactivity-parent
 * @description: 获取随机字符串(盐值)
 * @author: zouweidong
 * @create: 2018-08-03 13:58
 **/
public class RandomStringUtil {

    public static String getRandomString(int length) {
        StringBuilder buffer = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");
        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }
}
