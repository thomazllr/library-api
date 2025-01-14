package com.thomaz.library.controller;

import com.thomaz.library.model.Author;
import com.thomaz.library.model.dto.AuthorRequestDTO;
import com.thomaz.library.model.dto.AuthorResponseDTO;
import com.thomaz.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorRequestDTO request) {
        Author savedAuthor = service.save(request.toAuthor());

       var location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getId())
                .toUri();

        return ResponseEntity.created(location).build();
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

}
