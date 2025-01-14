package com.thomaz.library.repositories;

import com.thomaz.library.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;



    @Test
    void saveAuthor() {
        Author author = new Author();
        author.setName("Gui");
        author.setNationality("BR");
        repository.save(author);
    }

    @Test
    void deleteAuthor() {
        var author = repository.findById(UUID.fromString("0a71cd40-5aec-4432-af6e-b7a8cc381045")).orElse(null);
        repository.delete(author);
    }


}