package com.thomaz.library.service;


import com.thomaz.library.model.Author;
import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import com.thomaz.library.repositories.AuthorRepository;
import com.thomaz.library.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransationService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;


    @Transactional
    public void execute() {
        Author author = new Author();
        author.setName("John Doe");
        author.setNationality("English");
        authorRepository.save(author);

        if(author.getName().equals("John Doe")) {
            throw new RuntimeException("John Doe!!!!");
        }

        Book book = new Book();
        book.setTitle("livro epico");
        book.setGenre(Genre.SCIENCE);
        book.setAuthor(author);

        bookRepository.save(book);

    }


    @Transactional
    public void update()  {
        var book = bookRepository.findById(UUID.fromString("dcb3510d-c2c3-485c-88fb-279c62b994eb")).orElse(null);
    }


}
