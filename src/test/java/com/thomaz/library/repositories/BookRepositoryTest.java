package com.thomaz.library.repositories;

import com.thomaz.library.model.Author;
import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void saveBookInAuthor() {
        var author = authorRepository.findById(UUID.fromString("82cd42fa-4d02-44fe-a9cd-d3b97b32790b")).orElse(null);

        Book book = new Book();
        book.setTitle("O Senhor dos Anéis");
        book.setIsbn("978-3-16-148410-0");
        book.setPrice(BigDecimal.valueOf(200));
        book.setDescription("Uma épica história de aventura e amizade, onde um pequeno grupo de heróis é escolhido para destruir um anel poderoso que pode trazer a destruição ao mundo");
        book.setGenre(Genre.FANTASY);
        book.setRelease(LocalDate.of(1985, Month.FEBRUARY, 1));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setTitle("1984");
        book2.setIsbn("978-0-12-345678-9");
        book2.setPrice(BigDecimal.valueOf(100));
        book2.setDescription("Um romance distópico de George Orwell que explora temas como vigilância, totalitarismo e a manipulação da verdade");
        book2.setGenre(Genre.SCIENCE);
        book2.setRelease(LocalDate.of(1999, Month.JANUARY, 1));
        book2.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);
        author.getBooks().add(book2);

        bookRepository.save(book);
        bookRepository.save(book2);

        authorRepository.save(author);



    }

    @Test
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

}