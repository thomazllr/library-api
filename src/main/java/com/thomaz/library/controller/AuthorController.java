package com.thomaz.library.controller;

import com.thomaz.library.exceptions.DuplicateRegisterException;
import com.thomaz.library.exceptions.NotAllowedOperation;
import com.thomaz.library.model.Author;
import com.thomaz.library.model.dto.AuthorRequest;
import com.thomaz.library.model.dto.AuthorResponse;
import com.thomaz.library.model.dto.ErrorResponse;
import com.thomaz.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController implements GenericController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AuthorRequest request) {

        Author savedAuthor = service.save(request.toAuthor());
        var location = generateHeaderLocation(savedAuthor.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> details(@PathVariable String id) {
        return service.findById(id)
                .map(author -> ResponseEntity.ok(new AuthorResponse(author)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String nationality) {
        return ResponseEntity.ok(service.search(name, nationality)
                .stream()
                .map(AuthorResponse::new)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid AuthorRequest request) {


        Optional<Author> author = service.findById(id);
        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authorToUpdate = author.get();
        authorToUpdate.setName(request.name());
        authorToUpdate.setNationality(request.nationality());
        authorToUpdate.setBirthday(request.birthday());
        service.save(authorToUpdate);
        return ResponseEntity.noContent().build();


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        Optional<Author> author = service.findById(id);
        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(author.get());
        return ResponseEntity.noContent().build();

    }
}
