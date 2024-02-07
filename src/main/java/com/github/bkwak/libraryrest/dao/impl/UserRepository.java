package com.github.bkwak.libraryrest.dao.impl;

import com.github.bkwak.libraryrest.dao.IUserDAO;
import com.github.bkwak.libraryrest.model.Role;
import com.github.bkwak.libraryrest.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements IUserDAO {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1, "admin", DigestUtils.md5Hex("admin"), Role.ADMIN, "Ewa", "Nowak"));
        users.add(new User(2, "user", DigestUtils.md5Hex("user"), Role.USER, "Janusz", "Kowalski"));
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void persist(final User user) {
        this.users.stream()
                .map(User::getId)
                .max(Integer::compare)
                .ifPresentOrElse(
                        i -> user.setId(i + 1),
                        () -> user.setId(1)
                );
        this.users.add(user);
    }
}
