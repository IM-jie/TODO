package com.demo.utils.common;

import java.util.List;

/**
 * @program: parent
 * @description: 判空工具类
 * @author: huangjie
 * @create: 2018-08-28 17:20
 **/

public class EmptyUtil {
    /**
     * 判断对象为空
     *
     * @param obj 对象名
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof List)) {
            return ((List) obj).size() == 0;// NOSONAR
        }
        if ((obj instanceof String)) {
            return ((String) obj).trim().equals("");// NOSONAR
        }
        return false;
    }

    /**
     * 判断对象不为空
     *
     * @param obj 对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
