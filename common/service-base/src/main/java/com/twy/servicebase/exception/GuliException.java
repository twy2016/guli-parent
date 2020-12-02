package com.twy.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gongpeng
 * @date 2020/11/29 16:14
 */
@AllArgsConstructor
@Data
public class GuliException extends RuntimeException {
    private String msg;
}
