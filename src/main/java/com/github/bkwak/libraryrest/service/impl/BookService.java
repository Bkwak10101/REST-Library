package com.github.bkwak.libraryrest.service.impl;

import com.github.bkwak.libraryrest.dao.IBookDAO;
import com.github.bkwak.libraryrest.model.Book;
import com.github.bkwak.libraryrest.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return bookDAO.getByPattern(pattern);
    }

    public void persist(Book book) {
        this.bookDAO.persist(book);
    }

    @Override
    public void setInDb() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Spring in Action, Sixth Edition",
                "Craig Walls", "978-1617297571", true));
        books.add(new Book(2, "The Last Wish",
                "Andrzej Sapkowski", "978-1473226401", true));
        books.add(new Book(3, "Software Engineering (9th Edition)",
                "Ian Sommerville", "978-0137035151", true));
        books.add(new Book(4, "It",
                "Stephen King", "978-1501142970", true));

        for (Book book : books) {
            this.bookDAO.persist(book);
        }
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }
}
