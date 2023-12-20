//package com.ofektom.springsec.config;
//
//import com.ofektom.springsec.serviceImpl.UserServiceImpl;
//import com.ofektom.springsec.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//
//            //JWT EXTRACTING USERNAME TO VALIDATE USER
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            //JWT VALIDATING A TOKEN
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
////    @Autowired
////    private JWTServiceImpl jwtService;
////
////    @Autowired
////    private UserServiceImpl userService;
////
////    @Override
////    protected void doFilterInternal(@NonNull HttpServletRequest request,
////                                    @NonNull HttpServletResponse response,
////                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
////            final String authHeader = request.getHeader("Authorization");
////            final String jwt;
////            final String userEmail;
////
////            if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")){
////                filterChain.doFilter(request, response);
////                return;
////            }
////            jwt = authHeader.substring(7);
////            userEmail = jwtService.extractUserName(jwt);
////
////            if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
////                UserDetails userDetails = userService.loadUserByUsername(userEmail);
////
////                if(jwtService.isTokenValid(jwt, userDetails)){
////                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
////
////                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
////                            userDetails, null, userDetails.getAuthorities()
////                    );
////
////                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////
////                    securityContext.setAuthentication(token);
////                    SecurityContextHolder.setContext(securityContext);
////                }
////            }
////            filterChain.doFilter(request, response);
////
////    }
//}
