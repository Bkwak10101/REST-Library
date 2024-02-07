package com.github.bkwak.libraryrest.service;


import com.github.bkwak.libraryrest.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAll();
}
