package com.java.sm.exception;

import com.java.sm.result.AxiosStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName LoginException.java
 * @DescriPtion TODO
 * @CreateTime 2021年06月14日 23:06:00
 */
@Data
@AllArgsConstructor
public class LoginException extends RuntimeException{
    AxiosStatus axiosStatus;
}
