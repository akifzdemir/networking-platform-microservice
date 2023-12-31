package com.example.postservice.repositories;

import com.example.postservice.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByPost_Id(UUID postId);
    List<Comment> findByUserId(UUID userId);
}
