package com.example.musicdb.service;

import com.example.musicdb.model.dto.AlbumViewDTO;
import com.example.musicdb.model.dto.AlbumsByUserDTO;
import com.example.musicdb.model.dto.CreateAlbumDTO;
import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.entity.User;
import com.example.musicdb.repository.AlbumRepo;
import com.example.musicdb.repository.ArtistRepo;
import com.example.musicdb.repository.UserRepo;
import com.example.musicdb.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final AlbumRepo albumRepo;
    private final UserRepo userRepo;
    private final ArtistRepo artistRepo;
    private final LoggedUser loggedUser;

    @Autowired
    public AlbumService(AlbumRepo albumRepo, UserRepo userRepo, ArtistRepo artistRepo, LoggedUser loggedUser) {
        this.albumRepo = albumRepo;
        this.userRepo = userRepo;
        this.artistRepo = artistRepo;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateAlbumDTO createAlbumDTO) {

        Artist byName = this.artistRepo.findBySingerName(createAlbumDTO.getSingerName()).orElseThrow();

        Album album = new Album();
        album.setName(createAlbumDTO.getName());
        album.setImageUrl(createAlbumDTO.getImageUrl());
        album.setDescription(createAlbumDTO.getDescription());
        album.setCopies(createAlbumDTO.getCopies());
        album.setPrice(createAlbumDTO.getPrice());
        album.setReleaseDate(createAlbumDTO.getReleaseDate());
        album.setProducer(createAlbumDTO.getProducer());
        album.setGenre(createAlbumDTO.getGenre());
        album.setArtist(byName);
        album.setAddedFrom(getLoggedUser(loggedUser));

        this.albumRepo.save(album);

        return true;
    }

    private AlbumViewDTO viewAlbumDTO(Album album) {

        AlbumViewDTO albumViewDTO = new AlbumViewDTO();
                albumViewDTO.setId(album.getId());
                albumViewDTO.setImageUrl(album.getImageUrl());
                albumViewDTO.setName(album.getName());
                albumViewDTO.setArtist(album.getArtist());
                albumViewDTO.setGenre(album.getGenre());
                albumViewDTO.setPrice(album.getPrice());
                albumViewDTO.setReleaseDate(album.getReleaseDate());
                albumViewDTO.setCopies(album.getCopies());

                return albumViewDTO;
    }

    public Set<AlbumViewDTO> getAddedFromLoggedUser (User user) {

        return this.albumRepo.findAllByAddedFrom(getLoggedUser(loggedUser))
                .stream()
                .map(this::viewAlbumDTO)
                .collect(Collectors.toSet());
    }

    public AlbumsByUserDTO getAlbums() {

        AlbumsByUserDTO albums = new AlbumsByUserDTO();
        albums.setAlbums(getAddedFromLoggedUser(getLoggedUser(loggedUser)));

        return albums;
    }

    public int numOfCopies() {
        return  this.albumRepo.findAll().stream().mapToInt(Album::getCopies).sum();
    }
    public void removeAlbum(Long id) {
        this.albumRepo.deleteById(id);
    }

    public User getLoggedUser(LoggedUser loggedUser) {
        return this.userRepo.findById(loggedUser.getId()).orElseThrow();
    }
}
