package com.wzu.remote.common.jwt;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import java.util.Date;

public class JwtHelper {

    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    private static String tokenSignKey = "123456";

    /**
     * 根据用户id和用户名生成token
     * @param userId
     * @param username
     * @return token
     */
    public static String createToken(Long userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 根据token解密出用户id
     * @param token
     * @return userId
     */
    public static Long getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token解密出用户名
     * @param token
     * @return username
     */
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "Tom");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUsername(token));
    }
}