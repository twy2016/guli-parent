package com.twy.ucenter.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gongpeng
 * @date 2020/12/19 23:22
 */
@Component
public class ConstantPropertiesUtil {

    public static String appId;
    public static  String appSecret;
    public  static String redirectUrl;

    @Value("${wx.open.appId}")
    public void setAppId(String appId) {
        ConstantPropertiesUtil.appId = appId;
    }

    @Value("${wx.open.appSecret}")
    public void setAppSecret(String appSecret) {
        ConstantPropertiesUtil.appSecret = appSecret;
    }

    @Value("${wx.open.redirectUrl}")
    public void setRedirectUrl(String redirectUrl) {
        ConstantPropertiesUtil.redirectUrl = redirectUrl;
    }
}
