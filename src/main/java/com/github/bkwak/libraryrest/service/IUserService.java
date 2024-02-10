package com.github.bkwak.libraryrest.service;

import com.github.bkwak.libraryrest.model.User;

public interface IUserService {
    void persist(User user);
    boolean userExist(String login);
    void setInDb();
}
