package com.example.likebook.service;

import com.example.likebook.model.dto.CreatePostDTO;
import com.example.likebook.model.dto.PostByUserDTO;
import com.example.likebook.model.dto.PostViewDTO;
import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.Post;
import com.example.likebook.model.entity.User;
import com.example.likebook.repository.MoodRepo;
import com.example.likebook.repository.PostRepo;
import com.example.likebook.repository.UserRepo;
import com.example.likebook.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepo postRepo;

    private final MoodRepo moodRepo;

    private final LoggedUser loggedUser;

    private final UserRepo userRepo;

    public PostService(PostRepo postRepo, MoodRepo categoryRepo, LoggedUser loggedUser, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.moodRepo = categoryRepo;
        this.loggedUser = loggedUser;
        this.userRepo = userRepo;
    }

    public boolean create(CreatePostDTO createPostDTO) {

        Mood byName = this. moodRepo.findByName(createPostDTO.getMoodName()).orElseThrow();

        Post post = new Post();
        post.setContent(createPostDTO.getContent());
        post.setMood(byName);
        post.setUser(getLoggedUser(loggedUser));

        this.postRepo.save(post);

        return true;
    }

    private PostViewDTO viewPostDTO(Post post) {
        PostViewDTO postViewDTO = new PostViewDTO();
        postViewDTO.setId(post.getId());
        postViewDTO.setContent(post.getContent());
        postViewDTO.setUser(post.getUser());
        postViewDTO.setUserLikes(post.getUserLikes());
        postViewDTO.setMood(post.getMood());
        return postViewDTO;
    }

    public Set<PostViewDTO> getOwnPosts(User user) {

        return this.postRepo.findAllByUser(user)
                .stream()
                .map(this::viewPostDTO)
                .collect(Collectors.toSet());
    }

    public Set<PostViewDTO> getOtherPosts(User user) {

        return this.postRepo.findAllByUserNot(user)
                .stream()
                .map(this::viewPostDTO)
                .collect(Collectors.toSet());
    }

    public PostByUserDTO getPosts() {

        User logged = getLoggedUser(loggedUser);

        PostByUserDTO post = new PostByUserDTO();
        post.setLoggedUser(getOwnPosts(logged));
        post.setOthers(getOtherPosts(logged));

        return post;
    }

    public void removePost(Long id) {
        this.postRepo.deleteById(id);
    }

    public void likePost(Long id) {

        Post byId = this.postRepo.findById(id).orElseThrow();

        byId.getUserLikes().add(getLoggedUser(loggedUser));

        byId.setUserLikes(byId.getUserLikes());

        postRepo.save(byId);

    }

    public User getLoggedUser(LoggedUser loggedUser) {
        return this.userRepo.findById(loggedUser.getId()).orElseThrow();
    }
}


