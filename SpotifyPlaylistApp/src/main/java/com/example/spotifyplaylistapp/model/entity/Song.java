package com.example.spotifyplaylistapp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long duration;

    private LocalDate releaseDate;

    @ManyToOne
    private Style style;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
       this.users.remove(user);
    }

}
