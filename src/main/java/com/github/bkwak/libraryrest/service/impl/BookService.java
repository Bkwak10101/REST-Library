package com.github.bkwak.libraryrest.service.impl;

import com.github.bkwak.libraryrest.dao.IBookDAO;
import com.github.bkwak.libraryrest.model.Book;
import com.github.bkwak.libraryrest.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }
}
