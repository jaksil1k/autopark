package com.example.autopark.service.impl;

import com.example.autopark.model.Book;
import com.example.autopark.repository.BookRepository;
import com.example.autopark.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
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
    public void update(Book book) {
        if (bookRepository.findById(book.getId()).isEmpty()) {
           throw new RuntimeException();
        }
        bookRepository.save(book);
    }

    @Override
    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllActual() {
        return bookRepository.findAllActual(Calendar.getInstance().getTime());
    }
}
