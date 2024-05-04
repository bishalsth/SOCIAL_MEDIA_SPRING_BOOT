package com.social.Socail.Media.controller;

import com.social.Socail.Media.config.JwtProvider;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.repo.UserRepo;
import com.social.Socail.Media.request.LoginReq;
import com.social.Socail.Media.response.AuthResponse;
import com.social.Socail.Media.service.CustomUserDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailService userDetailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody User user){

        User isEmailExist  = userRepo.findByEmail(user.getEmail());
        if(isEmailExist !=null){
            throw new UsernameNotFoundException("email is already used please use another email");
        }
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFirstName(user.getFirstName());
        createdUser.setLastName(user.getLastName());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse =  new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Successfully account created");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);


    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody LoginReq req){

        String username = req.getEmail();
        String password = req.getPassword();

        Authentication authentication=authenticate(username,password);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse();
        authResponse.setMessage("succesfully logged in");
        authResponse.setJwt(jwt);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);


    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid email");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
