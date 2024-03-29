package com.github.bkwak.libraryrest.dao;


import com.github.bkwak.libraryrest.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    Optional<User> getById(int id);

    Optional<User> getByLogin(String login);

    List<User> getAll();

    void persist(User user);
}
