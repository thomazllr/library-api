package com.thomaz.library.service;

import com.thomaz.library.model.Author;
import com.thomaz.library.model.Book;
import com.thomaz.library.model.dto.BookRequest;
import com.thomaz.library.repositories.AuthorRepository;
import com.thomaz.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book save(BookRequest request) {
        Book book = request.toBook(authorRepository);
        return repository.save(book);
    }
}
