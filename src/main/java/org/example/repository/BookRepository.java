package org.example.repository;

import org.example.test_example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByAuthor(String author);

    Optional<Book> findByTitle(String title);
}
