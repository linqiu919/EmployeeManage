package com.java.sm.exception;

import com.java.sm.result.AxiosResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName ExceptionHandler.java
 * @DescriPtion TODO
 * @CreateTime 2021年06月14日 23:09:00
 */
@RestControllerAdvice  //@ControllerAdvice + @ResponseBody
public class LoginExceptionHandler {
    //此注解表示运行期抛出的异常由这个类处理
    @ExceptionHandler(LoginException.class)
    public AxiosResult<Void> handlerException(LoginException e){
        return AxiosResult.error(e.getAxiosStatus(),null);
    }
}
