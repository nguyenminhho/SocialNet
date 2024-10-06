package com.example.DACN.Repository;

import com.example.DACN.Entity.Chat;
import com.example.DACN.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {

//    @Query("SELECT c FROM Chat c WHERE c.user.id = :userId")
//    List<Chat> findAllChatByUserId(String userId);


}
