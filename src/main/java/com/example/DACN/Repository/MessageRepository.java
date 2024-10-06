package com.example.DACN.Repository;

import com.example.DACN.Entity.Chat;
import com.example.DACN.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    @Query("SELECT m FROM Message m WHERE m.user.id = :userId and  m.chat.id=:chatId")
    List<Message> findAllChatByUserId(String userId, String chatId);


}
