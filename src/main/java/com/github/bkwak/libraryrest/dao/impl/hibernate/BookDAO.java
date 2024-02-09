package com.github.bkwak.libraryrest.dao.impl.hibernate;


import com.github.bkwak.libraryrest.dao.IBookDAO;
import com.github.bkwak.libraryrest.model.Book;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {
    private final String GET_BY_ID = "FROM com.github.bkwak.libraryrest.model.Book WHERE id = :id";
    private final String GET_ALL = "FROM com.github.bkwak.libraryrest.model.Book";
    private final String GET_BY_PATTERN  = "FROM com.github.bkwak.libraryrest.model.Book WHERE title like :pattern OR author like :pattern OR isbn like :pattern";


    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<Book> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_ID,Book.class);
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
    public List<Book> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_ALL,Book.class);
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Book(id));
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(book);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_PATTERN,Book.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }
    
    public void persist(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }
}
