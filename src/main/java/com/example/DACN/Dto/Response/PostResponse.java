package com.example.DACN.Dto.Response;

import com.example.DACN.Entity.Comment;
import com.example.DACN.Entity.Image;
import com.example.DACN.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private String id;
    private String caption;
    private LocalDateTime createdAt;
    private String video;

    private List<Image> images;

    private List<User> liked;
    private List<Comment> comment;

    private User user;
}
