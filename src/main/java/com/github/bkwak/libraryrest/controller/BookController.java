package com.github.bkwak.libraryrest.controller;

import com.github.bkwak.libraryrest.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller 
public class BookController {

    @Autowired
    ILoanService loanService;

    @RequestMapping(path = "/loan/{bookId}", method = RequestMethod.GET)
    private String loanBook(@PathVariable final int bookId){
        loanService.persist(bookId);
        return "redirect:/main";
    }
}
