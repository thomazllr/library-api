package com.thomaz.library.repositories;

import com.thomaz.library.model.Author;
import com.thomaz.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    boolean existsByAuthor(Author author);

    Optional<Book> findByIsbn(String isbn);
}
