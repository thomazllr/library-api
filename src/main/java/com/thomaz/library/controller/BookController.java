package com.thomaz.library.controller;

import com.thomaz.library.exceptions.DuplicateRegisterException;
import com.thomaz.library.model.Book;
import com.thomaz.library.model.dto.BookRequest;
import com.thomaz.library.model.dto.ErrorResponse;
import com.thomaz.library.service.BookService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController implements GenericController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookRequest request) {
        Book book = service.save(request);
        var url = generateHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }
}
