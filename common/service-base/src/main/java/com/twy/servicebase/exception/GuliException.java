package com.twy.servicebase.exception;

import lombok.AllArgsConstructor;

/**
 * @author gongpeng
 * @date 2020/11/29 16:14
 */
@AllArgsConstructor
public class GuliException extends RuntimeException {
    private Integer code;
    private String msg;
}
