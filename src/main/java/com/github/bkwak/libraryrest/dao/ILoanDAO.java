package com.github.bkwak.libraryrest.dao;


import com.github.bkwak.libraryrest.model.Loan;

import java.util.List;


public interface ILoanDAO {
    List<Loan> getAllByID(int id);
    List<Loan> getAll();
    void persist(Loan loan);
    void bookReturn(int loanId);
}
