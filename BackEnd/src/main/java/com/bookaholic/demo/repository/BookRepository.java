package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    List<BookEntity> findAll();
    BookEntity findByBookId(UUID bookId);
    BookEntity save(BookEntity bookEntity);
    BookEntity findByISBN(String isbn);
}
