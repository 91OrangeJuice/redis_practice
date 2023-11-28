package com.tkp.learn.admin.security;

import com.tkp.learn.admin.model.common.JwtResultBo;
import com.tkp.learn.admin.util.JwtHelper;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Value("${config.jwt.expiration}")
    private Long expiration;

    @Value("${config.jwt.secret}")
    private String jwtSecret;

    @Value("${config.jwt.issuer}")
    private String issuer;

    /**
     * 生成JWT token
     *
     * @return
     */
    public String generateJWT(final String data) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("data", data);
        return JwtHelper.generateToken(issuer, data, claims, expiration, jwtSecret);
    }

    /**
     * 解析JWT
     *
     * @param jwt
     * @return
     */
    public JwtResultBo parseJWT(final String jwt) {
        JwtResultBo result = new JwtResultBo();
        Claims claims = getJwtClaims(jwt);
        if (ObjectUtils.isEmpty(claims)) {
            return null;
        }
        String date = sdf.format(claims.getExpiration());
        LOGGER.info("jwt -->[{}] 有效时间至-->[{}]", jwt, date);
        if(new Date().compareTo(claims.getExpiration())>0){
            return null;
        }
        if (ObjectUtils.isEmpty(claims.get("data"))) {
            return null;
        }
        result.setData((String) claims.get("data"));
        return result;
    }


    private Claims getJwtClaims(final String jwtToken) {
        Claims claims = null;
        try {
            claims = JwtHelper.getJwtClaims(jwtToken, jwtSecret);
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature --> {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token --> {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token --> {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token --> {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty --> {}", ex.getMessage());
        }
        return claims;
    }

}