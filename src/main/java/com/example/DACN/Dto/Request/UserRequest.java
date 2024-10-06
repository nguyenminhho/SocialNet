package com.example.DACN.Dto.Request;


import com.example.DACN.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String id;
    private String username;
    private String password;
    private String name;
    private Long age;
    private Set<String> roles;
    private List<Post> savedPost;
}
