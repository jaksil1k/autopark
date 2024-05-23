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
@RestController
public class PayController {
    private static Integer rate = 100;

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
        long hours = Math.round((double)(payDto.getEndTime().getTime() - payDto.getCreatedAt().getTime()) / 1000 / 60 / 60);
        payDto.setRoundedHours((int) hours);
        payDto.setPrice(payDto.getRoundedHours() * rate);
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
    @GetMapping("/rate")
    public ResponseEntity<Integer> getRate() {
        return ResponseEntity.ok(rate);
    }

    @PostMapping("/rate")
    public ResponseEntity<?> changeRate(@RequestParam Integer rate) {
        PayController.rate = rate;
        return ResponseEntity.ok().build();
    }
}
