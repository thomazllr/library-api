package com.thomaz.library.service;
import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import com.thomaz.library.model.dto.BookRequest;
import com.thomaz.library.repositories.AuthorRepository;
import com.thomaz.library.repositories.BookRepository;
import com.thomaz.library.repositories.specs.BookSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static com.thomaz.library.repositories.specs.BookSpecs.*;

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

    public Optional<Book> findById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public List<Book> search(String isbn, String nameAuthor, String title, Genre genre, LocalDate release) {
        Specification<Book> specification = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        return repository.findAll(specification);
    }
}
