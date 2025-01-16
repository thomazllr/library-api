package com.thomaz.library.controller;

import com.thomaz.library.exceptions.DuplicateRegisterException;
import com.thomaz.library.exceptions.NotAllowedOperation;
import com.thomaz.library.model.Author;
import com.thomaz.library.model.dto.AuthorRequestDTO;
import com.thomaz.library.model.dto.AuthorResponseDTO;
import com.thomaz.library.model.dto.ErrorResponse;
import com.thomaz.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AuthorRequestDTO request) {

        try {
            Author savedAuthor = service.save(request.toAuthor());

            var location = ServletUriComponentsBuilder.
                    fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedAuthor.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (DuplicateRegisterException e) {
            var error = ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> details(@PathVariable String id) {
        return service.findById(id)
                .map(author -> ResponseEntity.ok(new AuthorResponseDTO(author)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String nationality) {
        return ResponseEntity.ok(service.search(name, nationality)
                .stream()
                .map(AuthorResponseDTO::new)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody AuthorRequestDTO request) {

        try {
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
        } catch (DuplicateRegisterException e) {
            var error = ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            Optional<Author> author = service.findById(id);
            if (author.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            authorService.delete(author.get());
            return ResponseEntity.noContent().build();
        } catch (NotAllowedOperation e) {
            var error = ErrorResponse.defaultError(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }

    }
}
