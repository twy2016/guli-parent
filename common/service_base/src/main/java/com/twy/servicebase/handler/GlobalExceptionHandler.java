package com.twy.servicebase.handler;

import com.twy.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gongpeng
 * @date 2020/11/13 23:02
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        log.error(e.getMessage());
        return R.error("发生错误");
    }
}
