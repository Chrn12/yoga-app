package com.woniu.utils;

import com.woniu.yoga.domain.TStudent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //自定义的秘钥
    private static String sect = "laisdjfoiwqjflksaasfdasdfadsfwfrasdfsadffcasijfasadfasfdlkfjlasjflksajfd";
    //生成token
    public static String createToken(Object value, Integer mil) throws Exception{
        Key key = Keys.hmacShaKeyFor(sect.getBytes());
        Map<String, Object> map = new HashMap();
        Class<?> c = value.getClass();
        Field[] fields = c.getDeclaredFields();
        if(fields != null && fields.length > 1){
            for(Field f : fields){
                f.setAccessible(true);
                try {
                    Object put = map.put(f.getName(), f.get(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("111");
                }
            }
        }
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + mil * 60 * 1000))
                .signWith(key)
                .compact();

    }
    //解析token
    public static Claims parseToken(String token) throws Exception{
        Key key = Keys.hmacShaKeyFor(sect.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) throws Exception {
        Claims claims = parseToken("eyJhbGciOiJIUzUxMiJ9.eyJ0U3R1ZGVudElkIjoxLCJ0U3R1ZGVudFN0YXR1cyI6MCwidFN0dWRlbnRQYXNzd29yZCI6IjEyMzQ1NiIsInRTdHVkZW50Q3JlYXRlVGltZSI6eyJtb250aCI6Ik5PVkVNQkVSIiwieWVhciI6MjAyMCwiZGF5T2ZNb250aCI6OSwiaG91ciI6MTQsIm1pbnV0ZSI6MzEsIm1vbnRoVmFsdWUiOjExLCJuYW5vIjowLCJzZWNvbmQiOjE0LCJkYXlPZldlZWsiOiJNT05EQVkiLCJkYXlPZlllYXIiOjMxNCwiY2hyb25vbG9neSI6eyJpZCI6IklTTyIsImNhbGVuZGFyVHlwZSI6Imlzbzg2MDEifX0sInRTdHVkZW50VGVsIjoiMTIzNDU2Nzg5MTAiLCJleHAiOjE2MDQ5MjI3NjZ9.JVzALpBHeH3iucvcEssU4aypDkQNArp8E6_SGqURNYbN4SR0vBUOjUrEM0Ls9QFRNMUpu1XDuCJliND8NDsUHQ");
        System.out.println(claims);
    }
}
