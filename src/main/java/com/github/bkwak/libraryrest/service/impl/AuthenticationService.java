package com.github.bkwak.libraryrest.service.impl;

import com.github.bkwak.libraryrest.dao.IUserDAO;
import com.github.bkwak.libraryrest.model.User;
import com.github.bkwak.libraryrest.service.IAuthenticationService;
import com.github.bkwak.libraryrest.session.SessionObject;
import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionObject sessionObj;

    @Override
    public void login(String login, String passwd) {
        Optional<User> userFromDB = this.userDAO.getByLogin(login);
        if (userFromDB.isPresent() &&
                userFromDB.get().getPassword().equals(DigestUtils.md5Hex(passwd))) {
            this.sessionObj.setLoggedUser(userFromDB.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObj.setLoggedUser(null);
    }
}
