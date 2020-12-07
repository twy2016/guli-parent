package com.twy.vod.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gongpeng
 * @date 2020/12/7 22:35
 */
@Component
public class Constant {

    public static String keyId;
    public static String keySecret;

    @Value("${aliyun.vod.file.keyid}")
    public void setKeyId(String keyId) {
        Constant.keyId = keyId;
    }

    @Value("${aliyun.vod.file.keysecret}")
    public void setKeySecret(String keySecret) {
        Constant.keySecret = keySecret;
    }
}
