package com.thomaz.library.validator;

import com.thomaz.library.exceptions.DuplicateRegisterException;
import com.thomaz.library.exceptions.InvalidFieldException;
import com.thomaz.library.model.Book;
import com.thomaz.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookValidator {

    private static final int LIMITED_YEAR_REGISTER = 2020;
    @Autowired
    private BookRepository repository;

    public void validate(Book book) {
        if(existBookWithIsbn(book)) {
            throw new DuplicateRegisterException("Book with isbn already registered");
        }

        if(isPriceNull(book)) {
            throw new InvalidFieldException("price", "Books released before 2020 will be mandatory with prices.");
        }
    }

    private boolean isPriceNull(Book book) {
        return book.getPrice() == null && book.getRelease().getYear() >= LIMITED_YEAR_REGISTER;
    }

    private boolean existBookWithIsbn(Book book) {
        Optional<Book> byIsbn = repository.findByIsbn(book.getIsbn());

        if(book.getId()== null) {
            return byIsbn.isPresent();
        }

        return byIsbn.map(Book::getId).stream().anyMatch(id ->!id.equals(book.getId()));
    }
}
