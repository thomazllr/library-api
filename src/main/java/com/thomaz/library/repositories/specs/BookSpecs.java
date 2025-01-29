package com.thomaz.library.repositories.specs;

import com.thomaz.library.model.Book;
import com.thomaz.library.model.Genre;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + title.toUpperCase() +"%");
    }

    public static Specification<Book> genreEqual(Genre genre) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("genre"), genre));
    }

    public static Specification<Book> yearReleaseEqual(Integer year) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.equal(criteriaBuilder.function("to_char", String.class,
                root.get("release"), criteriaBuilder.literal("YYYY")), year.toString()));
    }

    public static Specification<Book> authorNameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("author").get("name")), "%" + name.toUpperCase() + "%");
    }






}
