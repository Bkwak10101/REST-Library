package com.github.bkwak.libraryrest.controller;

import com.github.bkwak.libraryrest.dao.IBookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CommonController {

    @Autowired
    IBookDAO bookDAO;

    @RequestMapping(path = {"/","/main","/index"}, method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("books", bookDAO.getAll());
        return "index";
    }

}
