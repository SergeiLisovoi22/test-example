package org.example.test_example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "secret_field")
    private String secret;

    @Column(name = "created_date")
    private LocalDateTime createdAt;

    public Book() {

    }

    public Book(Long id, String title, String author, String secret) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.secret = secret;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
