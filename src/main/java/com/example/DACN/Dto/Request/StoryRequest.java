package com.example.DACN.Dto.Request;

import com.example.DACN.Entity.Image;
import com.example.DACN.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryRequest {
    private String id;
    private String title;
    private Image image;
    private List<User> liked ;
    private User user;
    private LocalDateTime createdAt;
}
