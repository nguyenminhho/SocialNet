package com.example.DACN.Dto.Response;

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
public class ChatResponse {
    private String id;
    private String chatName;
    private String groupLead;

    private LocalDateTime createdAt;
    private Image chatImage;
    private List<User> user;
}
