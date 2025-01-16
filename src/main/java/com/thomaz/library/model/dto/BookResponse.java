package com.thomaz.library.model.dto;

import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookResponse(String isbn,
                           String title,
                           LocalDate release,
                           BigDecimal price,
                           Genre genre,
                           AuthorResponse author) {

    public BookResponse(Book book) {
        this(book.getIsbn(), book.getTitle(), book.getRelease(), book.getPrice(), book.getGenre(), new AuthorResponse(book.getAuthor()));
    }
}
