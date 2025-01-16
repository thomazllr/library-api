package com.thomaz.library.service;

import com.thomaz.library.exceptions.NotAllowedOperation;
import com.thomaz.library.model.Author;
import com.thomaz.library.repositories.AuthorRepository;
import com.thomaz.library.repositories.BookRepository;
import com.thomaz.library.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorValidator validator;

    @Autowired
    private BookRepository bookRepository;


    public Author save(Author author) {
        validator.validateAuthor(author);
        return repository.save(author);
    }

    public Optional<Author> findById(String id) {
       return repository.findById(UUID.fromString(id));
    }

    public List<Author> search(String name, String nationality) {

        if(name != null && nationality != null) {
            return repository.findByNameAndNationality(name, nationality);
        }

        if (name != null) {
            return repository.findByName(name);
        }

        if (nationality != null) {
            return repository.findByNationality(nationality);
        }

        return repository.findAll();
    }

    public void delete(Author author) {
        if(haveBook(author)) {
            throw new NotAllowedOperation("You are not allowed to delete this author");
        }
        repository.delete(author);
    }

    public boolean haveBook(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
