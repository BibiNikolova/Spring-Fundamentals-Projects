package com.example.PlannerApp.session;


import com.example.PlannerApp.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@Component
@SessionScope
public class LoggedUser {
    private long id;

    private String name;

    public void login(User user) {
        this.id = user.getId();
        this.name = user.getUsername();
    }

    public void logout() {
        this.id = 0;
        this.name = null;
    }


}