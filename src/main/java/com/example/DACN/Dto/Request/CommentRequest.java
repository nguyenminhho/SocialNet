package com.example.DACN.Dto.Request;

import com.example.DACN.Entity.Image;
import com.example.DACN.Entity.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String id;
    private String content;

    private LocalDateTime createdAt;
    private User user;
    private List<User> liked = new ArrayList<>();
}
