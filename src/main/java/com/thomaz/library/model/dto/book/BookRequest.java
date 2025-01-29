package com.thomaz.library.model.dto.book;

import com.thomaz.library.model.Author;
import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import com.thomaz.library.repositories.AuthorRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRequest(
        @NotBlank(message = "You need to provide the isbn") @ISBN String isbn,
        @NotBlank(message = "You need to provide the title") String title,
        @Past @NotNull(message = "You need to provide the release date") LocalDate release,
        BigDecimal price,
        Genre genre,
        @NotNull(message = "You need provide the Author ID") UUID idAuthor
) {
    public Book toBook(AuthorRepository repository) {
        Author author = repository.findById(idAuthor).orElse(null);
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setRelease(release);
        book.setPrice(price);
        book.setGenre(genre);
        book.setAuthor(author);
        return book;
    }


}
