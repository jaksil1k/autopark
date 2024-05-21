package com.example.autopark.controller;

import com.example.autopark.dto.EntityIdDto;
import com.example.autopark.dto.book.PayDto;
import com.example.autopark.model.Book;
import com.example.autopark.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getPay(@RequestParam String licensePlate) {
        Optional<Book> optionalBook = bookService.getPay(licensePlate);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Book book = optionalBook.get();

        if (book.getIsPayed()) {
            return ResponseEntity.notFound().build();
        }
        PayDto payDto = PayDto.toDto(book);
        payDto.setRoundedHours((int) (payDto.getEndTime().getTime() - payDto.getCreatedAt().getTime()));
        payDto.setPrice(payDto.getRoundedHours() * 100);
        return ResponseEntity.ok(payDto);
    }

    @PostMapping
    public ResponseEntity<?> pay(@RequestBody EntityIdDto idDto) {
        Optional<Book> optionalBook = bookService.getById(idDto.getId());

        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = optionalBook.get();
        book.setIsPayed(true);
        bookService.update(book);
        return ResponseEntity.ok().build();
    }
}
