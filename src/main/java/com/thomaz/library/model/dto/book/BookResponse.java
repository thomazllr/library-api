package com.thomaz.library.model.dto.book;

import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import com.thomaz.library.model.dto.author.AuthorResponse;

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
