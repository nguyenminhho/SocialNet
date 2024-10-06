package com.example.DACN.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String publicId;
    private String url;
    private String secureUrl;
//
//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
}
