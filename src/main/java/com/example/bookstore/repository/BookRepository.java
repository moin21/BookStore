package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM book e WHERE e.name = :bName", nativeQuery = true)
    List<Book> findBookByName(@Param("bName") String name);

    @Query(value = "SELECT * FROM book e WHERE e.id = :id", nativeQuery = true)
    Book getBooksById(@Param("id")int id);
}
