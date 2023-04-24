package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.CreateSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.dto.SongsByGenreDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final StyleRepository styleRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public SongService(SongRepository songRepository, StyleRepository styleRepository,
                       LoggedUser loggedUser, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(CreateSongDTO createSongDTO) {

        Style byName = extracted(createSongDTO.getStyleName());

        Song song = Song.builder()
                .performer(createSongDTO.getPerformer())
                .title(createSongDTO.getTitle())
                .releaseDate(createSongDTO.getReleaseDate())
                .duration(createSongDTO.getDuration())
                .style(byName)
                .users(null)
                .build();

        this.songRepository.save(song);

        return true;
    }

    private SongDTO viewSongDTO(Song song) {
        return SongDTO.builder()
                .id(song.getId())
                .duration(inMinutes(song.getDuration()))
                .performer(song.getPerformer())
                .title(song.getTitle())
                .style(song.getStyle())
                .build();
    }

    public Set<SongDTO> getPlaylist() {

        return getUser(loggedUser).getPlaylist()
                .stream()
                .map(this::viewSongDTO)
                .collect(Collectors.toSet());
    }

    public String totalTimePlaylist() {

        long sum = getUser(loggedUser).getPlaylist()
                .stream()
                .mapToLong(Song::getDuration)
                .sum();
        return inMinutes(sum);
    }

    public Set<SongDTO> getSongsByStyle(Style style) {
        return this.songRepository.findByStyle(style)
                .stream()
                .map(this::viewSongDTO)
                .collect(Collectors.toSet());
    }

    public SongsByGenreDTO getSongs() {

        SongsByGenreDTO songs = new SongsByGenreDTO();
        songs.setPopSongs(getSongsByStyle(extracted(StyleName.POP)));
        songs.setJazzSongs(getSongsByStyle(extracted(StyleName.JAZZ)));
        songs.setRockSongs(getSongsByStyle(extracted(StyleName.ROCK)));
        return songs;
    }

    public void addSongToPlaylist(Long songId) {

        User user = getUser(loggedUser);

        Song song = this.songRepository.findById(songId).orElseThrow();

        user.addSong(song);

        song.addUser(user);

        this.userRepository.save(user);
    }

    public void removeAllSongsFromPlaylist() {

        User user = getUser(loggedUser);

        user.getPlaylist()
                .forEach(s -> s.removeUser(user));

        user.removeAllSongsFromPlaylist();

        this.userRepository.save(user);
    }

    public String inMinutes(Long duration) {

        long min = duration / 60;
        long sec = duration % 60;

        return String.format("%02d:%02d", min, sec);
    }

    private Style extracted(StyleName styleName) {
        return this.styleRepository.findByName(styleName).orElseThrow();
    }

    private User getUser(LoggedUser loggedUser) {
        return this.userRepository.findById(loggedUser.getId()).orElseThrow();
    }

}
