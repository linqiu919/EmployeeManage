package com.java.sm.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName AxiosStatus.java
 * @CreateTime 2021年06月14日 22:46:00
 */
@Getter //lombok注解，表示生成get方法
@AllArgsConstructor //lombok注解，表示生成全参构造函数
public enum  AxiosStatus {
    OK(20000,"操作成功"),
    ERROR(40000,"操作失败"),
    ERROR_PHONE(50000,"手机号输入错误"),
    NOLOGIN(60000,"未登录"),
    ;
    private int status;
    private String message;
}
