package com.github.bkwak.libraryrest.dao;


import com.github.bkwak.libraryrest.model.Loan;

import java.util.List;
import java.util.Optional;


public interface ILoanDAO {
    List<Loan> getAll();
    List<Loan> getAllById(int id);
    List<Loan> getAllLoaned();
    List<Loan> getAllOverdue();
    Optional<Loan> getById(int id);
    void persist(Loan loan);
    void bookReturn(Loan loan);
}
