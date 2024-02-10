package com.github.bkwak.libraryrest.service;

import com.github.bkwak.libraryrest.model.Loan;

import java.util.List;

public interface ILoanService {
    void persist(int bookId);
    List<Loan> getAllById(int id);
    List<Loan> getAll();
    List<Loan> getAllLoaned();
    List<Loan> getAllOverdue();
    void bookReturn(int loanId);
}
