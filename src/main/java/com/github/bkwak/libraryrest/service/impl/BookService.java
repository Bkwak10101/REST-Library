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
        books.add(new Book(1, "Java: The Complete Reference, Twelfth Edition",
                "Herbert Schildt", "978-83-832-2156-4", true));
        books.add(new Book(2, "C# 12 in a Nutshell",
                "Joseph Albahari", "978-10-981-4740-2", true));
        books.add(new Book(3, "Python Data Science Handbook: Essential Tools for Working with Data, 2nd Edition",
                "Jake VanderPlas", "978-83-289-0068-4", true));
        books.add(new Book(4, "SQL QuickStart Guide: The Simplified Beginner's Guide to Managing, Analyzing, and Manipulating Data With SQL",
                "Walter Shields", "978-83-832-2657-6", true));

        for (Book book : books) {
            this.bookDAO.persist(book);
        }
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }
}
