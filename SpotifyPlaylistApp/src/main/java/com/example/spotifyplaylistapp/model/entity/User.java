package com.example.spotifyplaylistapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false )
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Song> playlist;

    public void addSong(Song song) {
        this.playlist.add(song);
    }
    public void removeAllSongsFromPlaylist(){
        this.playlist.clear();
    }

}
