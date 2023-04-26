package com.example.coffeeshopapp.service;


import com.example.coffeeshopapp.model.dto.CreateOrderDTO;
import com.example.coffeeshopapp.model.entity.Category;
import com.example.coffeeshopapp.model.entity.Order;
import com.example.coffeeshopapp.model.entity.User;
import com.example.coffeeshopapp.repository.CategoryRepository;
import com.example.coffeeshopapp.repository.OrderRepository;
import com.example.coffeeshopapp.repository.UserRepository;
import com.example.coffeeshopapp.session.LoggedUser;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, CategoryRepository categoryRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(CreateOrderDTO createOrderDTO) {

        Category byName = this.categoryRepository.findByName(createOrderDTO.getCategoryName()).orElseThrow();

        Order order = Order.builder()
                .name(createOrderDTO.getName())
                .price(createOrderDTO.getPrice())
                .orderTime(createOrderDTO.getOrderTime())
                .description(createOrderDTO.getDescription())
                .category(byName)
                .employee(getUser(loggedUser))
                .build();

        this.orderRepository.save(order);

        return true;
    }
//
//
//    private SongDTO viewSongDTO(Song song) {
//        SongDTO songDTO = new SongDTO();
//        songDTO.setId(song.getId());
//        songDTO.setDuration(inMinutes(song.getDuration()));
//        songDTO.setPerformer(song.getPerformer());
//        songDTO.setTitle(song.getTitle());
//        songDTO.setStyle(song.getStyle());
//        return songDTO;
//    }
//
//    public Set<SongDTO> getPlaylist() {
//
//        return this.songRepository.findByUserId(loggedUser.getId())
//                .stream()
//                .map(this::viewSongDTO)
//                .collect(Collectors.toSet());
//    }
//
//    public String totalTimePlaylist() {
//        long sum = this.songRepository.findByUserId(loggedUser.getId())
//                .stream()
//                .mapToLong(Song::getDuration)
//                .sum();
//        return inMinutes(sum);
//    }
//
//    public Set<SongDTO> getSongsByStyle(Style style) {
//        return this.songRepository.findByStyle(style)
//                .stream()
//                .map(this::viewSongDTO)
//                .collect(Collectors.toSet());
//    }
//
//    public SongsByGenreDTO getSongs() {
//
//        SongsByGenreDTO songs = new SongsByGenreDTO();
//        songs.setPopSongs(getSongsByStyle(extracted(StyleName.POP)));
//        songs.setJazzSongs(getSongsByStyle(extracted(StyleName.JAZZ)));
//        songs.setRockSongs(getSongsByStyle(extracted(StyleName.ROCK)));
//        return songs;
//    }
//
//    public void addSongToPlaylist(Long songId) {
//
//        User user = getUser(loggedUser);
//        Song song = this.songRepository.findById(songId).orElseThrow();
//
//        user.getPlaylist().add(song);
//
//        user.setPlaylist(user.getPlaylist());
//
//        this.userRepository.save(user);
//    }
//
//    public void removeAllSongsFromPlaylist() {
//
//        User user = getUser(loggedUser);
//
//        user.getPlaylist().clear();
//
//        user.setPlaylist(user.getPlaylist());
//
//        this.userRepository.save(user);
//    }
//
//    public String inMinutes(Long duration){
//
//        long min = duration/60;
//        long sec = duration % 60;
//
//        return String.format("%02d:%02d", min, sec);
//
//    }
//
//    private Style extracted(StyleName styleName) {
//        return this.styleRepository.findByName(styleName);
//    }
//
    private User getUser(LoggedUser loggedUser) {
        return this.userRepository.findById(loggedUser.getId()).orElseThrow();
    }

}
