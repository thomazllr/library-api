package com.thomaz.library.service;

import com.thomaz.library.exceptions.NotAllowedOperation;
import com.thomaz.library.model.Author;
import com.thomaz.library.repositories.AuthorRepository;
import com.thomaz.library.repositories.BookRepository;
import com.thomaz.library.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
        var author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Author> authorExample = Example.of(author, matcher);
        return repository.findAll(authorExample);
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
