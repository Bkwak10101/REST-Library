package com.github.bkwak.libraryrest.controller;

import com.github.bkwak.libraryrest.service.IBookService;
import com.github.bkwak.libraryrest.session.SessionObject;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CommonController {

    @Autowired
    IBookService bookService;
    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = {"/","/main","/index"}, method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("books", this.bookService.getAll());
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        return "index";
    }

}
