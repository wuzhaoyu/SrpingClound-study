package com.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;

import java.util.Date;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/25
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class CreateJwt {
    public static void main(String[] args) {
        //载荷
        JwtBuilder builder = Jwts.builder()
                .setId("1111")
                .setSubject("wzy")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 60000))
                .claim("role","admin");//一分钟后过期
        builder.signWith(SignatureAlgorithm.HS256,"first");
        System.out.println(builder.compact());
    }
}
