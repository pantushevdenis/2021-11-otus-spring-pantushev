package ru.otus.spring.pantushev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.pantushev.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
    Стратегия загрузки данных:
    поля многие-к-одному (справочники) грузим сразу
    дочерние объекты один-ко-многим, грузим лениво.
 */

@Service
public class BooksDaoJpa
    implements BooksDao
{
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public BooksDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public long getCount() {
        return em.createQuery("""
            select count(b) 
            from Book b
            """, Long.class).getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("""
            select b 
            from Book b
            join fetch b.author
            join fetch b.jenre
            """, Book.class).getResultList();
    }

    @Override
    public Book insert(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Book getById(long id) {
        return em.createQuery("""
            select b 
            from Book b
            join fetch b.author
            join fetch b.jenre
            where b.id = :id
            """, Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public void delete(long id) {
        Book b = em.find(Book.class, id);
        if (b == null) {
            throw new EntityNotFoundException("Book with id = " + id + " not found.");
        }
        em.remove(b);
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }
}
