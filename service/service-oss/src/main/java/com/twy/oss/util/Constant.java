package com.twy.oss.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gongpeng
 * @date 2020/11/22 16:29
 */
@Component
public class Constant {

    public static String endpoint;
    public static String accessKeyId;
    public static String accessKeySecret;
    public static String bucketName;

    @Value("${oss.endpoint}")
    public void setEndpoint(String endpoint) {
        Constant.endpoint = endpoint;
    }

    @Value("${oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        Constant.accessKeyId = accessKeyId;
    }

    @Value("${oss.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        Constant.accessKeySecret = accessKeySecret;
    }

    @Value("${oss.bucketName}")
    public void setBucketName(String bucketName) {
        Constant.bucketName = bucketName;
    }
}
