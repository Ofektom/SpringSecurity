//package com.ofektom.springsec.util;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import javax.xml.bind.DatatypeConverter;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.io.Serializable;
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class JwtUtil implements Serializable {
//    private static final long serialVersionUID = -2550185165626007488L;
//
//    @Value("${jwt.expiration}")
//    private String expiration;
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("Role", userDetails.getAuthorities());
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//
//
//    //GENERATING A SECRET
//    public static String generateRandomSecret(){
//        return DatatypeConverter.printBase64Binary(new byte[512/8]);
//    }
//
//    //CREATING OUR SECRET-KEY FROM THE SECRET...
//    public static Key getSecretKey() {
//        byte[] secretInBytes =  DatatypeConverter.parseBase64Binary(generateRandomSecret());
//        return new SecretKeySpec(secretInBytes, "HmacSHA512");
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    public String extractUserRole(String token) {
//        Claims claims =  extractAllClaims(token);
//        return claims.get("Role").toString();
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
//    }
//
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder().setClaims(claims).setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(expiration)))
//                .signWith(getSecretKey(), SignatureAlgorithm.HS512).compact();
//    }
//
//}