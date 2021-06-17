package com.java.sm.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName AxiosResult.java
 * @CreateTime 2021年06月14日 22:42:00
 */
@Data
//次注解表示对象转字符串时，只给前端传递不为null的值
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AxiosResult<T> {
    private int status;
    private String message;
    private T data;
    private AxiosResult(){

    }

    public static <T> AxiosResult<T> success(){
        return setData(AxiosStatus.OK,null);
    }

    public static <T> AxiosResult<T> success(T t){
        return setData(AxiosStatus.OK,t);
    }

    public static <T> AxiosResult<T> success(AxiosStatus axiosStatus,T t){
        return setData(axiosStatus,t);
    }


    public static <T> AxiosResult<T> error(){
        return setData(AxiosStatus.ERROR,null);
    }

    public static <T> AxiosResult<T> error(T t){
        return setData(AxiosStatus.ERROR,t);
    }


    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus,T t){
        return setData(axiosStatus,t);
    }


    public static <T> AxiosResult<T> setData(AxiosStatus axiosStatus,T t){
        AxiosResult<T> axiosResultT = new AxiosResult<>();
        axiosResultT.setStatus(axiosStatus.getStatus());
        axiosResultT.setMessage(axiosStatus.getMessage());
        axiosResultT.setData(t);
        return axiosResultT;
    }
}
