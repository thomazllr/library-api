package com.thomaz.library.validator;

import com.thomaz.library.exceptions.DuplicateRegisterException;
import com.thomaz.library.model.Author;
import com.thomaz.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidator {

    @Autowired
    private AuthorRepository authorRepository;

    public void validateAuthor(Author author) {
        if(isDuplicateAuthor(author)) {
            throw new DuplicateRegisterException("Author already exists");
        }
    }

    private boolean isDuplicateAuthor(Author author) {
        var optionalAuthor = authorRepository.findByNameAndNationalityAndBirthday(
                author.getName(),
                author.getNationality(),
                author.getBirthday());

        if (author.getId() == null) {
            return optionalAuthor.isPresent();
        }

        return optionalAuthor.isPresent() && !author.getId().equals(optionalAuthor.get().getId());
    }

}
