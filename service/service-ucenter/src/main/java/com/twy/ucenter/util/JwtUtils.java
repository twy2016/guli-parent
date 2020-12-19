package com.twy.ucenter.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author gongpeng
 * @date 2020/12/19 17:00
 */
@Component
@PropertySource(value = "classpath:application.yml")
public class JwtUtils {

    private static long expire;
    private static String secret;

    @Value("${jwt.expire}")
    public void setExpire(long expire) {
        JwtUtils.expire = expire;
    }

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtUtils.secret = secret;
    }

    /**
     * 获取token
     *
     * @param id
     * @param nickname
     * @return
     */
    public static String getToken(String id, String nickname) {
        return Jwts.builder().setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .claim("id", id)
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setSubject("guli-user").compact();
    }

    /**
     * 判断token是否存在与有效
     *
     * @param token
     * @return
     */
    public static Boolean checkToken(String token) {
        if (!StrUtil.isEmpty(token)) {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        return false;
    }

    /**
     * 根据token获取用户id
     *
     * @param token
     * @return
     */
    public static String getIdByToken(String token) {
        if (!StrUtil.isEmpty(token)) {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return (String) body.get("id");
        }
        return null;
    }
}
