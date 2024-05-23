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
import java.util.Optional;

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
//        Optional<Book> optionalBook = bookService.getById(book.getId());
//        Book book1 = optionalBook.get();
//        book1.setName(book.getName());
//        book1.setSlot(book.getSlot());
//        book1.setPhone(book.getPhone());
//        book1.setFloor(book.getFloor());
//        book1.setIsPayed(book.getIsPayed());
//        book1.setLicensePlate(book.getLicensePlate());
//        book1.setCreatedAt(book.getCreatedAt());

        return ResponseEntity.ok(bookService.update(book));
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
