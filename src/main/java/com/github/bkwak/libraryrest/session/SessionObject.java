package com.github.bkwak.libraryrest.session;

import com.github.bkwak.libraryrest.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser;

    public boolean isLogged() {
        return loggedUser != null;
    }

    public boolean isAdmin(){
        if (isLogged()){
            if(loggedUser.getRole().name().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


}
