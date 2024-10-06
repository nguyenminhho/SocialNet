package com.example.DACN.Repository;

import com.example.DACN.Entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findAllPostByUserId(String userId);


}
