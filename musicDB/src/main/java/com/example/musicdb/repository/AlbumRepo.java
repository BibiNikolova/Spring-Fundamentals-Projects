package com.example.musicdb.repository;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {
        Set<Album> findAllByAddedFrom(User user);

}
