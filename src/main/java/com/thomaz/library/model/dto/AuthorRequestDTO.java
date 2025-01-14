package com.thomaz.library.model.dto;

import com.thomaz.library.model.Author;

import java.time.LocalDate;

public record AuthorRequestDTO(String name, LocalDate birthday, String nationality) {

    public Author toAuthor() {
            Author author = new Author();
            author.setName(name);
            author.setNationality(nationality);
            author.setBirthday(birthday);
            return author;
    }
}
