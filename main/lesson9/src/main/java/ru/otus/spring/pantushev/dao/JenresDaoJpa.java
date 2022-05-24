package ru.otus.spring.pantushev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.pantushev.domain.Jenre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class JenresDaoJpa
    implements JenresDao
{
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public JenresDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Jenre> getAll() {
        return em.createQuery("select j from Jenre j", Jenre.class).getResultList();
    }

    @Override
    public Jenre getByid(long id) {
        return em.find(Jenre.class, id);
    }
}
