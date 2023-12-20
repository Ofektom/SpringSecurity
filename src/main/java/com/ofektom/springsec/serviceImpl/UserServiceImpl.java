package com.ofektom.springsec.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofektom.springsec.dto.JwtAuthenticationResponse;
import com.ofektom.springsec.dto.SignUpRequest;
import com.ofektom.springsec.dto.SigninRequest;
import com.ofektom.springsec.entities.Users;
import com.ofektom.springsec.enums.Role;
import com.ofektom.springsec.repository.UserRepository;
import com.ofektom.springsec.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.function.Function;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Function<SignUpRequest, Users> signup = (signUpRequest -> {
        Users users = new ObjectMapper().convertValue(signUpRequest, Users.class);
//        users.setEmail(signUpRequest.getEmail());
//        users.setFirstname(signUpRequest.getFirstname());
//        users.setLastname(signUpRequest.getLastname());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole(Role.USER);
        return userRepository.save(users);
    });
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        Users users = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        return null;
    }

}
