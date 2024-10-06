package com.example.DACN.Dto.Request;

import com.example.DACN.Entity.Comment;
import com.example.DACN.Entity.Image;
import com.example.DACN.Entity.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String id;
    private String chatName;
    private String groupLead;

    private LocalDateTime createdAt;
    private Image chatImage;
    private List<User> user;
}
