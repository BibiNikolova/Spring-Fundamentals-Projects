package com.example.likebook.repository;

import com.example.likebook.model.entity.Post;
import com.example.likebook.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    Set<Post> findAllByUser(User user);

    Set<Post> findAllByUserNot(User user);
}
