package com.example.DACN.Dto.Response;

import com.example.DACN.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private String id;
    private String content;

    private LocalDateTime createdAt;
    private User user;
    private List<User> liked = new ArrayList<>();
}
