package com.example.DACN.Repository;

import com.example.DACN.Entity.Comment;
import com.example.DACN.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    @Query("SELECT c FROM Comment c WHERE c.user.id = :userId")
    List<Comment> findAllCommentByUserId(String userId);


}
