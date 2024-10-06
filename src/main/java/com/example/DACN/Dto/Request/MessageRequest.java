package com.example.DACN.Dto.Request;

import com.example.DACN.Entity.Chat;
import com.example.DACN.Entity.Image;
import com.example.DACN.Entity.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String id;
    private String content;

    private LocalDateTime createdAt;
    private List<Image> image;

    private User user;

    private Chat chat;


}
