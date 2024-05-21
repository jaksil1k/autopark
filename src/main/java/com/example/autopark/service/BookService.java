package com.example.autopark.service;

import com.example.autopark.dto.book.BookDto;
import com.example.autopark.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    void save(Book book);

    List<Book> getAll();

    Optional<Book> getById(UUID id);

    void update(Book book);

    void delete(UUID id);

    List<Book> getAllActual();

    Optional<Book> getPay(String licensePlate);
}
