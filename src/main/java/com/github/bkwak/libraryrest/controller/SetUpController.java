package com.github.bkwak.libraryrest.controller;

import com.github.bkwak.libraryrest.service.IBookService;
import com.github.bkwak.libraryrest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SetUpController {

    @Autowired
    IBookService bookService;
    @Autowired
    IUserService userService;

    @RequestMapping(path = "/set/book", method = RequestMethod.GET)
    private String bookInit(){
        this.bookService.setInDb();
        return "redirect:/main";
    }
    @RequestMapping(path = "/set/user", method = RequestMethod.GET)
    private String userInit(){
        this.userService.setInDb();
        return "redirect:/main";
    }
}
