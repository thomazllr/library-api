package com.thomaz.library.repositories;

import com.thomaz.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    
    List<Author> findByNameAndNationality(String name, String nationality);

    List<Author> findByName(String name);

    List<Author> findByNationality(String nationality);
}
