package com.example.musicdb.repository;

import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.enums.SingerName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {

    Optional<Artist> findBySingerName(SingerName singerName);
}
