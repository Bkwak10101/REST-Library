package com.github.bkwak.libraryrest.dao.impl.memory;

import com.github.bkwak.libraryrest.dao.IBookDAO;
import com.github.bkwak.libraryrest.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepository implements IBookDAO {
    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        this.books.add(new Book(1, "Spring in Action, Sixth Edition",
                "Craig Walls", "978-1617297571", true));
        this.books.add(new Book(2, "The Last Wish",
                "Andrzej Sapkowski", "978-1473226401", true));
        this.books.add(new Book(3, "Software Engineering (9th Edition)",
                "Ian Sommerville", "978-0137035151", true));
        this.books.add(new Book(4, "It",
                "Stephen King", "978-1501142970", true));
    }

    @Override
    public Optional<Book> getById(final int id) {
        return this.books.stream().filter(book -> book.getId() == id).findFirst();
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public void delete(final int id) {
        this.books.removeIf(book -> book.getId() == id);
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(pattern.toLowerCase()) ||
                        book.getTitle().toLowerCase().contains(pattern.toLowerCase()))
                .toList();
    }

    @Override
    public void persist(Book book) {

    }
}
