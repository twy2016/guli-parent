package com.twy.commonutils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gongpeng
 * @date 2020/11/13 22:01
 */
@Data
@ApiModel("响应信息主体")
public class R<T> implements Serializable {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public static <T> R<T> ok() {
        return restResult(true, null, ResultCode.SUCCESS, (String) null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(true, data, ResultCode.SUCCESS, (String) null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(true, data, ResultCode.SUCCESS, msg);
    }

    public static <T> R<T> error() {
        return restResult(false, null, ResultCode.ERROR, (String) null);
    }

    public static <T> R<T> error(String msg) {
        return restResult(false, null, ResultCode.ERROR, msg);
    }

    public static <T> R<T> error(T data) {
        return restResult(false, data, ResultCode.ERROR, (String) null);
    }

    public static <T> R<T> error(T data, String msg) {
        return restResult(false, data, ResultCode.ERROR, msg);
    }

    private static <T> R<T> restResult(boolean success, T data, int code, String msg) {
        R<T> apiResult = new R();
        apiResult.setSuccess(success);
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }

}
