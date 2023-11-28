package com.tkp.learn.web.security;

import com.tkp.learn.web.model.bo.JwtResultBo;
import com.tkp.learn.web.service.UserService;
import com.tkp.learn.web.service.factory.StrategyContext;
import com.tkp.learn.web.utils.JwtHelper;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Autowired
    private StrategyContext<UserService> context;

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
    public String generateJWT(final String data, final String identity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("identity", identity);
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
        if (ObjectUtils.isEmpty(claims.get("data")) || ObjectUtils.isEmpty(claims.get("identity"))) {
            return null;
        }
        result.setData((String) claims.get("data"));
        result.setIdentity((String) claims.get("identity"));
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

    /**
     * 读取用户信息
     *
     * @param result jwt解析结果
     * @return
     */
    public SecurityUser loadUserById(final JwtResultBo result) {
        final String identity = result.getIdentity();
        final String workNo = result.getData();
        final UserService userService = context.getStrategy(identity);
        return userService.loadUserByWorkNo(workNo);
    }

}