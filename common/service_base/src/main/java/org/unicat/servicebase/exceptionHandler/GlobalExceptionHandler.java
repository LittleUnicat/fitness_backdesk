package org.unicat.servicebase.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.unicat.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  // 处理什么类型的异常
    @ResponseBody   // 为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error()
                .message("执行了全局异常处理...");
    }


    @ExceptionHandler(MyException.class)  // 处理什么类型的异常
    @ResponseBody   // 为了返回数据
    public R error(MyException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error()
                .code(e.getCode())
                .message(e.getMsg());
    }
}
