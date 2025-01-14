package com.thomaz.library.service;

import com.thomaz.library.model.Author;
import com.thomaz.library.model.dto.AuthorResponseDTO;
import com.thomaz.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public Author save(Author author) {
        return repository.save(author);
    }

    public Optional<Author> findById(String id) {
       return repository.findById(UUID.fromString(id));
    }

    public List<Author> search(String name, String nationality) {

        if(name != null && nationality != null) {
            return repository.findByNameAndNationality(name, nationality);
        }

        if (name != null) {
            return repository.findByName(name);
        }

        if (nationality != null) {
            return repository.findByNationality(nationality);
        }

        return repository.findAll();
    }
}
