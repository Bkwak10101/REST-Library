package com.github.bkwak.libraryrest.service.impl;

import com.github.bkwak.libraryrest.dao.IUserDAO;
import com.github.bkwak.libraryrest.model.Role;
import com.github.bkwak.libraryrest.model.User;
import com.github.bkwak.libraryrest.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public void persist(User user) {
        this.userDAO.persist(user);
    }

    @Override
    public boolean userExist(String login) {
        return false;
    }

    @Override
    public void setInDb() {
        List<User> users = new ArrayList<>();
        users.add(new User("admin", DigestUtils.md5Hex("admin"), Role.ADMIN, "Ewa", "Nowak"));
        users.add(new User("user", DigestUtils.md5Hex("user"), Role.USER, "Janusz", "Kowalski"));

        for (User user : users) {
            this.userDAO.persist(user);
        }
    }
}
