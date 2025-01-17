package com.thomaz.library.service;

import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import com.thomaz.library.model.dto.BookRequest;
import com.thomaz.library.repositories.AuthorRepository;
import com.thomaz.library.repositories.BookRepository;
import com.thomaz.library.validator.BookValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    @Autowired
    private BookValidator validator;

    public Book save(BookRequest request) {
        Book book = request.toBook(authorRepository);
        validator.validate(book);
        return repository.save(book);
    }

    public void update(String id, BookRequest request) {
        findById(id).map(book -> {
            Book entity = request.toBook(authorRepository);
            book.setRelease(entity.getRelease());
            book.setPrice(entity.getPrice());
            book.setGenre(entity.getGenre());
            book.setTitle(entity.getTitle());
            book.setAuthor(entity.getAuthor());
            book.setIsbn(entity.getIsbn());
            validator.validate(book);
            return repository.save(book);
        }).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public Optional<Book> findById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public Page<Book> search(String isbn, String nameAuthor, String title, Genre genre, Integer release, Integer page, Integer size) {
        Specification<Book> specification = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (isbn != null) {
            specification = specification.and(isbnEqual(isbn));
        }

        if (title != null) {
            specification = specification.and(titleLike(title));
        }

        if (genre != null) {
            specification = specification.and(genreEqual(genre));
        }

        if (release != null) {
            specification = specification.and(yearReleaseEqual(release));
        }

        if (nameAuthor != null) {
            specification = specification.and(authorNameLike(nameAuthor));
        }

        var request = PageRequest.of(page, size);

        return repository.findAll(specification, request);
    }
}
