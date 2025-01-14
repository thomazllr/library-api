package com.thomaz.library.model.dto;

import com.thomaz.library.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDTO(UUID id, String name, LocalDate birthday, String nationality) {

    public AuthorResponseDTO(Author author) {
        this(author.getId(), author.getName(), author.getBirthday(), author.getNationality());
    }
}
