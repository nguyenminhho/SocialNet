package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.AuthenticateRequest;
import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Request.VerifierTokenRequest;
import com.example.DACN.Dto.Response.AuthenticateResponse;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Dto.Response.VerifierTokenResponse;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Service.AuthenticateService;
import com.example.DACN.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000") // Cho phép từ nguồn này
@RequestMapping("/api/v1")
public class AuthenticateController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticateService authenticateService;
    @PostMapping("/login")
     ResponseEntity<AuthenticateResponse> authenticated(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticateService.authenticated(authenticateRequest));
    }

//    @PostMapping("/loginWithGG")
//    ResponseEntity<AuthenticateResponse> loginWithGoogleAuth2(@RequestParam("code") String code) throws Exception {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(authenticateService.loginWithGoogle(code));
//    }


    @PostMapping("/logout")
    ResponseEntity<Void> logOut(@RequestBody VerifierTokenRequest verifierTokenRequest) throws Exception {
               authenticateService.logOut(verifierTokenRequest);
               return ResponseEntity.ok().build();
    }

    @PostMapping("/verifier")
    ResponseEntity<VerifierTokenResponse> verified(@RequestBody VerifierTokenRequest verifierTokenRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticateService.verifier(verifierTokenRequest));
    }






}
