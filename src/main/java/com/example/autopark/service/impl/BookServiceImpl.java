package com.example.autopark.service.impl;

import com.example.autopark.model.Book;
import com.example.autopark.repository.BookRepository;
import com.example.autopark.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getById(UUID id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book update(Book book) {
        if (bookRepository.findById(book.getId()).isEmpty()) {
           throw new RuntimeException();
        }
        return bookRepository.save(book);
    }

    @Override
    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllActual() {
        return bookRepository.findAllByIsPayed(false);
    }

    @Override
    public Optional<Book> getPay(String licensePlate) {
        return bookRepository.findByLicensePlateOrderByCreatedAtDesc(licensePlate);
    }

    @Override
    public Boolean checkForPayed(String licensePlate) {
        return bookRepository.findAllByLicensePlateAndIsPayed(licensePlate, false).isEmpty();
    }

}
