package ru.otus.spring.pantushev.services.data;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DictionnariesServiceImpl implements DictionnariesService{
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;

    @Autowired
    public DictionnariesServiceImpl(AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }

    @Override
    public Optional<Author> findAuthorById(ObjectId id) {
        return authorsRepository.findById(id);
    }

    @Override
    public List<Author> findAuthorsAll() {
        return  authorsRepository.findAll();
    }

    @Override
    public List<Author> findAuthorsByUserIdIsEndingWith(String str) {
        return authorsRepository.findAuthorsByUserIdIsEndingWith(str);
    }

    @Override
    public Optional<Jenre> findJenreById(ObjectId id) {
        return jenresRepository.findById(id);
    }

    @Override
    public List<Jenre> findJenreAll() {
        return jenresRepository.findAll();
    }

    @Override
    public List<Jenre> findJenresByUserIdIsEndingWith(String str) {
        return jenresRepository.findByUserIdIsEndingWith(str);
    }

    @Override
    public List<ru.otus.spring.pantushev.domain.dropdownLists.Author> findAllAuthorsList() {
        List<ru.otus.spring.pantushev.domain.dropdownLists.Author> al = authorsRepository.findAllList();
        return al;
    }

    @Override
    public List<ru.otus.spring.pantushev.domain.dropdownLists.Jenre> findAllJenresList() {
        List<ru.otus.spring.pantushev.domain.dropdownLists.Jenre> al = jenresRepository.findAllList();
        return al;
    }


}
