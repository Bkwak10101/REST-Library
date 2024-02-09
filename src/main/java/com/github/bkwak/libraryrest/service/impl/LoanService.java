package com.github.bkwak.libraryrest.service.impl;

import com.github.bkwak.libraryrest.dao.ILoanDAO;
import com.github.bkwak.libraryrest.model.Book;
import com.github.bkwak.libraryrest.model.Loan;
import com.github.bkwak.libraryrest.service.IBookService;
import com.github.bkwak.libraryrest.service.ILoanService;
import com.github.bkwak.libraryrest.session.SessionObject;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService implements ILoanService {

    @Autowired
    ILoanDAO loanDAO;
    @Autowired
    IBookService bookService;
    @Resource
    SessionObject sessionObj;
    @Override
    public void persist(int bookId) {
        Book book = this.bookService.getById(bookId).get();
        book.setAvailable(false);
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(this.sessionObj.getLoggedUser());
        loan.setStartDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusDays(20));
        loanDAO.persist(loan);
    }
}
