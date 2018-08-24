package com.demo.utils.common;

import com.demo.entity.common.Result;

/**
 * @program: parent
 * @description: Result工具类
 * @author: zouweidong
 * @create: 2018-08-23 09:35
 **/
public class ResultUtil {

    /**
     * 当正确时返回的值
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("OK");
        result.setData(data);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    /**
     * 当错误时返回的值
     */
    public static Result error(int code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
