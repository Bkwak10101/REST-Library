package com.github.bkwak.libraryrest.dao.impl.hibernate;

import com.github.bkwak.libraryrest.dao.ILoanDAO;
import com.github.bkwak.libraryrest.model.Loan;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanDAO implements ILoanDAO {

    private final String LOAN_BY_ID = "FROM com.github.bkwak.libraryrest.model.Loan WHERE id = :id";
    private final String ALL_LOANS = "FROM com.github.bkwak.libraryrest.model.Loan";
    private final String ALL_LOANS_BY_USER_ID =
            "FROM com.github.bkwak.libraryrest.model.Loan WHERE user.id = :user_id";
    private final String ALL_LOANED_BOOKS =
            "FROM com.github.bkwak.libraryrest.model.Loan WHERE returnDate IS NULL";
    private final String ALL_OVERDUE_LOANS =
            "FROM com.github.bkwak.libraryrest.model.Loan " +
            "WHERE (returnDate IS NULL AND DATEDIFF(endDate,current_date ) <= -1)" +
            "OR (returnDate IS NOT NULL AND DATEDIFF(endDate, returnDate ) <= -1)";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Loan> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery(ALL_LOANS, Loan.class);
        List<Loan> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Loan> getAllById(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery(ALL_LOANS_BY_USER_ID,Loan.class);
        query.setParameter("user_id", userId);
        List<Loan> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Loan> getAllLoaned() {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery(ALL_LOANED_BOOKS,Loan.class);
        List<Loan> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Loan> getAllOverdue() {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery(ALL_OVERDUE_LOANS,Loan.class);
        List<Loan> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Loan> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery(LOAN_BY_ID,Loan.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }finally {
            session.close();
        }
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
    public void bookReturn(Loan loan) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(loan);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
