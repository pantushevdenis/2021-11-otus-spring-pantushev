package ru.otus.spring.pantushev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.pantushev.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AuthorsDaoJpa
    implements AuthorsDao
{
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public AuthorsDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }
}
