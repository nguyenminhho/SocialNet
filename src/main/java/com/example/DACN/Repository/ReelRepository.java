package com.example.DACN.Repository;

import com.example.DACN.Entity.Reels;
import com.example.DACN.Entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelRepository extends JpaRepository<Reels, String> {

    @Query("SELECT r FROM Reels r WHERE r.user.id = :userId")
    List<Reels> findReelsByUserId(String userId);


}
