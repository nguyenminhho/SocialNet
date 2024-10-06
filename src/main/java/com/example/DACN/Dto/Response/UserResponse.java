package com.example.DACN.Dto.Response;


import com.example.DACN.Entity.Post;
import com.example.DACN.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.LICENSE;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String username;
//    private String password;
    private String name;
    private Long age;
    private Set<Roles> roles;
    private List<Post> savedPost;
}
