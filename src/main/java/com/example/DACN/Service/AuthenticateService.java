package com.example.DACN.Service;

import com.example.DACN.Dto.Request.AuthenticateRequest;
import com.example.DACN.Dto.Request.GGTokenRequest;
import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Request.VerifierTokenRequest;
import com.example.DACN.Dto.Response.AuthenticateResponse;
import com.example.DACN.Dto.Response.VerifierTokenResponse;
import com.example.DACN.Entity.InvalidToken;
import com.example.DACN.Entity.Roles;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.*;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
//@RequiredArgsConstructor
@Slf4j
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    InvalidTokenRepository invalidTokenRepository;
@Autowired
    LoginWithGG loginWithGG;

    @Autowired
   OnBoughtUser onBoughtUser;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private UserMapper userMapper;

    @Value("${jwt.singerKey}")
    protected  String SINGER_KEY;

//
//    @NonFinal
//    @Value("${google.client-Id}")
//    protected  String CLIENT_ID;
//
//    @NonFinal
//    @Value("${google.client-secret}")
//    protected  String CLIENT_SECRET;
//    @NonFinal
//    @Value("${google.redirect-uri}")
//    protected  String REDIRECT_URI;
//
//
//
//    @NonFinal
//    protected final  String GRANT_TYPE="authorization_code";
//
//
//    public AuthenticateResponse loginWithGoogle(String code){
//
//        var result = loginWithGG.exchangeToken(GGTokenRequest.builder()
//                .code(code)
//                .clientId(CLIENT_ID)
//                .clientSecret(CLIENT_SECRET)
//                .redirectUri(REDIRECT_URI)
//                .grantType(GRANT_TYPE)
//                .build());
//
//        var userInfo = onBoughtUser.getUSerInfo("json",result.getAccessToken());
//
//        Set<Roles> roles = new HashSet<>();
//        Roles rolesUSER = rolesRepository.findById("USER").get();
//        roles.add(rolesUSER);
//
//        var user = userRepository.findByUserName(userInfo.getEmail());
//        if(user == null){
//            userRepository.save(User.builder()
//                            .username(userInfo.getEmail())
//                            .name(userInfo.getGivenName())
//                            .roles(roles)
//                    .build());
//        }
//
//        var token = generateToken(user);
//        log.info("token: " +token);
//        return AuthenticateResponse.builder()
//                .token(token)
//                .authenticated(true)
//                .build();
//    }
//

    public AuthenticateResponse authenticated(AuthenticateRequest authenticateRequest) throws Exception {
        User user = userRepository.findByUserName(authenticateRequest.getUsername());
        if( user == null) {
            throw new Exception("User exits");
        }

        boolean authenticated = passwordEncoder.matches(authenticateRequest.getPassword(), user.getPassword());
        if(!authenticated){
            throw new Exception("Password is wrong");
        }

        var token = generateToken(user);
        return AuthenticateResponse.builder()
                .authenticated(true)
                .token(token)
                .build();
    }



    private String generateToken(User user){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("hoang")
                .issueTime(new Date())
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
        try {
            jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot generate token", e);
            throw new RuntimeException(e);
        }
    }

    public String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())){
             user.getRoles().forEach(roles ->
             {
                 stringJoiner.add(roles.getName());
                 if(!CollectionUtils.isEmpty(roles.getPermissions()))
                 {
                    roles.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                 }
             });
        }

        return stringJoiner.toString();
    }

    public void logOut(VerifierTokenRequest verifierTokenRequest) throws Exception {
        var signToken = verifyToken(verifierTokenRequest.getToken());

        String id = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidToken invalidToken = InvalidToken.builder()
                .id(id)
                .expiryTime(expiryTime)
                .build();
        invalidTokenRepository.save(invalidToken);

    }

    public VerifierTokenResponse verifier(VerifierTokenRequest verifierTokenRequest) throws Exception {
        var token = verifierTokenRequest.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        }catch (Exception e){
            isValid = false;
        }


        return VerifierTokenResponse.builder()
                .verifier(isValid)
                .build();

    }


    //Kiem tra xem token co dung, con thoi gian hay da bi log out
    public SignedJWT verifyToken(String token) throws Exception {
        JWSVerifier verifier = new MACVerifier(SINGER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);

        if(!verified && expTime.after(new Date())){
            throw new Exception("Token wrong hoac da het han");
        }
        var exitsToken = invalidTokenRepository.findById(signedJWT.getJWTClaimsSet().getJWTID());

        if(exitsToken.isPresent()) {
            throw new Exception("Token da het han");
        }

            return signedJWT;
    }



}
