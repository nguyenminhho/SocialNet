package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.PermissionRequest;
import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Response.PermissionResponse;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Service.PermissionService;
import com.example.DACN.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/addPermission")
     ResponseEntity<PermissionResponse> addPermission(@RequestBody PermissionRequest permissionRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(permissionService.addPermission(permissionRequest));
    }

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/allPermission")
     ResponseEntity<List<PermissionResponse>> getAllPermission(){
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.getAllPermission());
    }



    @DeleteMapping("/deletePermission/{id}")
    public ResponseEntity<Boolean> deletePermission(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).body(permissionService.removePermission(id));
    }



}
