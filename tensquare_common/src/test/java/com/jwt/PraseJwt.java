package com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/25
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class PraseJwt {
    public static void main(String[] args) {
        Claims claims = Jwts.parser()
                .setSigningKey("first")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTExIiwic3ViIjoid3p5IiwiaWF0IjoxNTgyNjQwOTI0LCJleHAiOjE1ODI2NDA5ODQsInJvbGUiOiJhZG1pbiJ9._OWPWK7CoDUtUavpbIzDNcTeYI-Pv1xJL1o2MKTsg0s")
                .getBody();
        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名subject:" + claims.getSubject());
        System.out.println("登录IssuedAt:" + claims.getIssuedAt());
        System.out.println("过期IssuedAt:" + claims.getExpiration());
        System.out.println("权限:" + claims.get("role"));
    }
}
