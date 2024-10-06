package com.example.DACN.Repository;

import com.example.DACN.Entity.Post;
import com.example.DACN.Entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, String> {

    @Query("SELECT s FROM Story s WHERE s.user.id = :userId")
    List<Story> findStoryByUserId(String userId);


}
