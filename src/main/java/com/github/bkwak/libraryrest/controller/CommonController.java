package com.github.bkwak.libraryrest.controller;

import com.github.bkwak.libraryrest.model.Book;
import com.github.bkwak.libraryrest.service.IBookService;
import com.github.bkwak.libraryrest.service.ILoanService;
import com.github.bkwak.libraryrest.session.SessionObject;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

    @Autowired
    IBookService bookService;
    @Autowired
    ILoanService loanService;
    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = {"/", "/main", "/index"}, method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        model.addAttribute("rented", this.loanService.getAllLoaned());
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        model.addAttribute("isAdmin",
                this.sessionObject.isAdmin());
        return "index";
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("pattern", "");
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        model.addAttribute("isAdmin",
                this.sessionObject.isAdmin());
        return "search";
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("pattern") String pattern, Model model) {
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        model.addAttribute("isAdmin",
                this.sessionObject.isAdmin());
        model.addAttribute("books", bookService.getByPattern(pattern));
        return "search";
    }

    @RequestMapping(path = "/addBook", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        model.addAttribute("isAdmin",
                this.sessionObject.isAdmin());
        return "addBook";
    }

    @RequestMapping(path = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book, Model model) {
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        model.addAttribute("isAdmin",
                this.sessionObject.isAdmin());
        bookService.persist(book);
        return "redirect:/main";
    }
}