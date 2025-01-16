package com.thomaz.library.controller;

import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import com.thomaz.library.model.dto.BookRequest;
import com.thomaz.library.model.dto.BookResponse;
import com.thomaz.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController implements GenericController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid BookRequest request) {
        Book book = service.save(request);
        var url = generateHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable String id) {
        return service.findById(id)
                .map(book -> ResponseEntity.ok(new BookResponse(book)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return service.findById(id)
                .map(b -> {
                    service.delete(b);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> search(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Genre genre,
            @RequestParam(value = "author-name", required = false) String nameAuthor,
            @RequestParam(required = false) Integer release
    ) {
        var result = service.search(isbn, nameAuthor, title, genre, release)
                .stream()
                .map(BookResponse::new)
                .toList();

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid BookRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }
}
