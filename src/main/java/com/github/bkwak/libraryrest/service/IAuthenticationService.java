package com.github.bkwak.libraryrest.service;

public interface IAuthenticationService {
    void login(String login, String passwd);
    void logout();
}
