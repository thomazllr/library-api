package com.thomaz.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 30,     nullable = false)
    private Genre genre;

    @Column(name = "price",precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "release")
    private LocalDate release;

    @ManyToOne
    @JoinColumn(name = "id_author")
        private Author author;

    @CreatedDate
    @Column(name = "date_register")
    private LocalDateTime dateRegister;

    @LastModifiedDate
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    private UUID idClient;


    public Book() {
    }

    public Book(UUID id, String title, String description, Genre genre, Author author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.author = author;
    }

    public Book(UUID id, String title, String isbn, String description, Genre genre, BigDecimal price, LocalDate release, Author author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.release = release;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }
}
