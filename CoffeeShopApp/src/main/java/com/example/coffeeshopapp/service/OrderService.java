package com.example.coffeeshopapp.service;


import com.example.coffeeshopapp.model.dto.CreateOrderDTO;
import com.example.coffeeshopapp.model.dto.EmployeesOrdersDTO;
import com.example.coffeeshopapp.model.dto.OrderViewDTO;
import com.example.coffeeshopapp.model.dto.OrdersByCategory;
import com.example.coffeeshopapp.model.entity.Category;
import com.example.coffeeshopapp.model.entity.Order;
import com.example.coffeeshopapp.model.entity.User;
import com.example.coffeeshopapp.model.enums.CategoryName;
import com.example.coffeeshopapp.repository.CategoryRepository;
import com.example.coffeeshopapp.repository.OrderRepository;
import com.example.coffeeshopapp.repository.UserRepository;
import com.example.coffeeshopapp.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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

    public Long getTotalTimeToPrepare() {
        return this.orderRepository.findAll()
                .stream()
                .mapToLong(o -> o.getCategory().getNeededTime())
                .sum();
    }

    private OrderViewDTO viewOrder(Order order) {

        return OrderViewDTO.builder()
                .id(order.getId())
                .name(order.getName())
                .price(order.getPrice())
                .category(order.getCategory())
                .build();
    }
    public Set<OrderViewDTO> getOrderByCategory(Category category) {
        return this.orderRepository.findByCategory(category)
                .stream()
                .map(this::viewOrder)
                .collect(Collectors.toSet());
    }

    public OrdersByCategory getAllOrders(){
        return OrdersByCategory.builder()
                .drinks(getOrderByCategory(extracted(CategoryName.DRINK)))
                .cakes(getOrderByCategory(extracted(CategoryName.CAKE)))
                .coffees(getOrderByCategory(extracted(CategoryName.COFFEE)))
                .others(getOrderByCategory(extracted(CategoryName.OTHER)))
                .build();
    }

    private EmployeesOrdersDTO viewEmployeesOrders(User user){
        return EmployeesOrdersDTO.builder()
                .employeeName(user.getUsername())
                .numOfOrders(user.getOrders().size())
                .build();
    }

    public Set<EmployeesOrdersDTO> getOrdersByEmployee() {

        return this.userRepository.findAll()
                .stream()
                .map(this::viewEmployeesOrders)
                .collect(Collectors.toSet());
    }

    public void addOrder(Long orderId) {

        User user = getUser(loggedUser);
        Order order = this.orderRepository.findById(orderId).orElseThrow();

        user.addOrder(order);

        this.userRepository.save(user);
    }


    public void removeOrder(Long orderId) {

        User user = getUser(loggedUser);
        Order order = this.orderRepository.findById(orderId).orElseThrow();

        user.getOrders().remove(order);

        this.orderRepository.delete(order);

        this.userRepository.save(user);
    }

    public String inMinutes(Long duration){

        long min = duration/60;
        long sec = duration % 60;

        return String.format("%02d:%02d", min, sec);

    }

    private Category extracted(CategoryName categoryName) {
        return this.categoryRepository.findByName(categoryName).orElseThrow();
    }

    private User getUser(LoggedUser loggedUser) {
        return this.userRepository.findById(loggedUser.getId()).orElseThrow();
    }

}
