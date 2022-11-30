package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    List<BookEntity> findAll();
    BookEntity findByBookId(UUID bookId);
    BookEntity save(BookEntity bookEntity);
    BookEntity findByIsbn(String isbn);
    List<BookEntity> findByTeacherId(UUID teacherId);

    @Query(value="SELECT * from book WHERE book_id IN (SELECT book_id from enroll WHERE student_id = :studentId)", nativeQuery=true)
    List<BookEntity> queryBooksByStudentId(UUID studentId);
}
