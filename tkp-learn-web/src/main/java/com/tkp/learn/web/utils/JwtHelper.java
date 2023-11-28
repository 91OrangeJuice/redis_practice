package com.tkp.learn.web.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;

public class JwtHelper {

    private JwtHelper() {
        throw new IllegalStateException("JwtHelper class");
    }

    /**
     * 生成JWT
     *
     * @param issuer     签发者 本系统名称
     * @param subject    主题可以是用户名或者ID
     * @param claims     自定义参数
     * @param expiration 超时时间秒
     * @param secret     加密算法
     * @return
     */
    public static String generateToken(String issuer, String subject, Map<String, Object> claims, Long expiration,
                                       String secret) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        return Jwts.builder()
                .setId(RandomUtils.randomGUID())
                .setIssuer(issuer)
                .setSubject(subject)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, apiKeySecretBytes).compact();
    }

    /**
     * 获取 jwt claims
     *
     * @param jwtToken
     * @param secret
     * @return
     */
    public static Claims getJwtClaims(String jwtToken, String secret) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * 校验JWT Token
     *
     * @param jwtToken
     * @param secret
     */
    public static void validJwtToken(String jwtToken, String secret) {
        Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwtToken);
    }

}
