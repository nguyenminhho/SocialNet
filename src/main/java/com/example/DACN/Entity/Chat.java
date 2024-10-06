package com.example.DACN.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String chatName;

    private String groupLead;

    private LocalDateTime createdAt;
    @OneToOne
    private Image chatImage;
    @ManyToMany
    private List<User> user = new ArrayList<>();



}
