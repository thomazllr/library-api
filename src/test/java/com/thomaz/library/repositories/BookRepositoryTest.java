package com.thomaz.library.repositories;

import com.thomaz.library.model.Author;
import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        var author = authorRepository.findById(UUID.fromString("dbbc3f91-ea7b-43a0-a888-3973b40e3a7e")).orElse(null);

        Book book = new Book();
        book.setTitle("livro epico");
        book.setDescription("é um livro epico");
        book.setGenre(Genre.SCIENCE);
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setTitle("livro epico 2");
        book2.setDescription("é massa");
        book2.setGenre(Genre.SCIENCE);
        book2.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);
        author.getBooks().add(book2);

        authorRepository.save(author);



    }

    @Test
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

}