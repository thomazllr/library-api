package com.thomaz.library.model.dto;

import com.thomaz.library.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorRequest(
        @NotBlank(message = "You need to provide the name")
        @Size(max = 100, message = "Field out of the pattern")
        String name,
        @NotNull(message = "You need to provide the birthday")
        @Past(message = "Not allowed future dates")
        LocalDate birthday,
        @NotBlank(message = "You need to provide the nationality") String nationality) {

    public Author toAuthor() {
            Author author = new Author();
            author.setName(name);
            author.setNationality(nationality);
            author.setBirthday(birthday);
            return author;
    }
}
