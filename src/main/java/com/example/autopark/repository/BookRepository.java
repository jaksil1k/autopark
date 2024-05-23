package com.example.autopark.repository;

import com.example.autopark.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findAllByIsPayed(Boolean isPayed);

    Optional<Book> findByLicensePlateOrderByCreatedAtDesc(String licensePlate);

    Optional<Book> findAllByLicensePlateAndIsPayed(String licensePlate, Boolean isPayed);
}
