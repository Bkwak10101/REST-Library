package com.github.bkwak.libraryrest.dao.impl.hibernate;

import com.github.bkwak.libraryrest.dao.ILoanDAO;
import com.github.bkwak.libraryrest.model.Loan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanDAO implements ILoanDAO {
    private final String GET_ALL_BY_ID = "FROM com.github.bkwak.libraryrest.model.loan WHERE id = :id";
    private final String GET_ALL = "FROM com.github.bkwak.libraryrest.model.loan";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Loan> getAllByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery(GET_ALL_BY_ID, Loan.class);
        query.setParameter("id", id);
        List<Loan> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Loan> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery(GET_ALL, Loan.class);
        List<Loan> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void persist(Loan loan) {
        Session session = this.sessionFactory.openSession();
        loan.setBook(session.merge(loan.getBook()));
        try {
            session.beginTransaction();
            session.persist(loan);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void bookReturn(int loanId) {

    }
}
