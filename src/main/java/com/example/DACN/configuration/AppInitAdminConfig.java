package com.example.DACN.configuration;

import com.example.DACN.Entity.Roles;
import com.example.DACN.Entity.User;
import com.example.DACN.Repository.RolesRepository;
import com.example.DACN.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class AppInitAdminConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;
    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            if(userRepository.findByUserName("admin") == null){
                HashSet<Roles> roles = new HashSet<>();
                Roles rolesUSER = rolesRepository.findById("ADMIN").get();
                roles.add(rolesUSER);
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                log.warn("Admin had been created");
                userRepository.save(user);
            }
        };

    }
}
