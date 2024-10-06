package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.AddPasswordRequest;
import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @PostMapping("/addUser")
     ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(userRequest));
    }

    @PostMapping("/addPassword")
    ResponseEntity<Void> addPassword(@RequestBody AddPasswordRequest addPasswordRequest) throws Exception {
        userService.addPassword(addPasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/allUser")
     ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }


    @GetMapping("/my-info")
    ResponseEntity<UserResponse> currentUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMyInfo());
    }


    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable  String id, @RequestBody UserRequest userRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,userRequest));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(id));
    }



}
