package com.example.DACN.Service;

import com.example.DACN.Dto.Request.AddPasswordRequest;
import com.example.DACN.Dto.Request.UserRequest;

import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Entity.Post;
import com.example.DACN.Entity.Roles;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.RolesRepository;
import com.example.DACN.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    public UserResponse addUser(UserRequest userRequest) throws Exception{
        User user = userMapper.toUser(userRequest);
        if( (userRepository.findByUserName(user.getUsername())) != null){
            throw  new Exception("User exits");
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        List<Post> posts = new ArrayList<>();
        user.setSavedPost(posts);
        HashSet<Roles> roles = new HashSet<>();
        Roles rolesUSER = rolesRepository.findById("USER").get();
        roles.add(rolesUSER);
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void addPassword(AddPasswordRequest addPasswordRequest) throws Exception{
        var userCurrent = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(userCurrent.getName());
        if( user == null || (StringUtils.hasText(user.getPassword()))){
            throw  new Exception("User isn't exits or User had password");
        }
        user.setPassword(passwordEncoder.encode(addPasswordRequest.getPassword()));
       userRepository.save(user);

    }

    public List<UserResponse> getAllUser(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id).get());
    }

    public UserResponse getMyInfo(){
        var currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        return userMapper.toUserResponse(userRepository.findByUserName(username));
    }

    ///con chua sua role khi update co role thay doi
    public UserResponse updateUser(String id, UserRequest userRequest) throws Exception {
        User user = userRepository.findById(id).get();

        user.setId( userRequest.getId() );
        user.setPassword( userRequest.getPassword() );
        user.setName( userRequest.getName() );
        user.setAge( userRequest.getAge() );
//        if (userRequest.getRoles() != null) {
//            if (user.getRoles() != null) {
//                user.getRoles().clear();
//                user.getRoles().addAll(userRequest.getRoles());
//            } else {
//                user.setRoles(new LinkedHashSet<>(userRequest.getRoles()));
//            }
//        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }




    public Boolean removeUser(String id){
        userRepository.deleteById(id);

        return true;
    }


}
