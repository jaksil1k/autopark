package com.example.autopark.repository;

import com.example.autopark.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(value = "SELECT * from books where created_at + interval '1 hour' * hours > NOW()", nativeQuery = true)
    List<Book> findAllActual(Date date);

    Optional<Book> findByLicensePlateOrderByCreatedAtDesc(String licensePlate);
}
