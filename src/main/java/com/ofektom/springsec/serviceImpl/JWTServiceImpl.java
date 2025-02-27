//package com.ofektom.springsec.serviceImpl;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//
//@Service
//public class JWTServiceImpl implements Serializable {
//    public String generateToken(UserDetails userDetails){
//        return Jwts.builder().setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *24))
//                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractUserName(String token){
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
//        final Claims claims = extractAllClaims(token);
//        return claimsResolvers.apply(claims);
//    }
//
//    @Value("${jwt.token.secret}")
//    private String secret;
//
//    private Key getSigninKey(){
//        byte[] key = Decoders.BASE64.decode(this.secret);
//        return Keys.hmacShaKeyFor(key);
//    }
//
//    private Claims extractAllClaims(String token){
//        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails){
//        final String username = extractUserName(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//
//    private boolean isTokenExpired(String token){
//        return extractClaim(token, Claims::getExpiration).before(new Date());
//    }
//
//}
