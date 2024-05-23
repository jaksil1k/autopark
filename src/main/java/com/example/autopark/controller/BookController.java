package com.example.autopark.controller;

import com.example.autopark.dto.EntityIdDto;
import com.example.autopark.dto.book.BookDto;
import com.example.autopark.model.Book;
import com.example.autopark.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        if (!bookService.checkForPayed(book.getLicensePlate())) {
            return ResponseEntity.badRequest().build();
        }
        book.setIsPayed(false);
        bookService.save(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/actual")
    public ResponseEntity<?> getAllActualBooks() {
        return ResponseEntity.ok(bookService.getAllActual());
    }
    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        bookService.update(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestBody EntityIdDto idDto) {
        bookService.delete(idDto.getId());
        return ResponseEntity.ok().build();
    }

    private boolean getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken);
    }
}
