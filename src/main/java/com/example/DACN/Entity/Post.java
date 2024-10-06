package com.example.DACN.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String caption;
    private LocalDateTime createdAt;
    private String video;

    @OneToMany
    private List<Image> images;

    @OneToMany
    private List<User> liked;
    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comment = new ArrayList<>();

}
