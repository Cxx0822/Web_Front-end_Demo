package com.web.demo.service.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil {
    // 私有签名
    private static final String SING = "6Dx8SIuaHXJYnpsG18SSpjPs50lZcT52";

    /*
     * 生成token
     */
    public static String getToken(Map<String, String> map){
        String token = null;

        Calendar instance = Calendar.getInstance();
        // 默认7天过期
        instance.add(Calendar.DATE, 7);

        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // 添加payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        // 指定令牌过期时间
        builder.withExpiresAt(instance.getTime());

        // sign
        token = builder.sign(Algorithm.HMAC256(SING));

        return token;
    }

    /*
     * 验证token
     */

    public static void verify(String token) throws Exception{
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /*
     * 获取信息
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}