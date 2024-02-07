package com.github.bkwak.libraryrest.controller;

import com.github.bkwak.libraryrest.model.User;
import com.github.bkwak.libraryrest.service.IAuthenticationService;
import com.github.bkwak.libraryrest.session.SessionObject;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthController {

    @Autowired
    IAuthenticationService authenticationService;
    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model) {
        model.addAttribute("isLogged",
                sessionObject.isLogged());

        this.authenticationService.login(user.getLogin(), user.getPassword());

        if (this.sessionObject.isLogged()) {
            model.addAttribute("logged", true);
            return "redirect:/main";
        } else {
            model.addAttribute("logged", false);
            return "login";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }
}
