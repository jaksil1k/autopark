package com.example.autopark.dto.book;

import com.example.autopark.model.Book;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class BookDto {
    private Integer floor;
    private Integer slot;

    public static List<BookDto> toDto(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(BookDto
                    .builder()
                    .floor(book.getFloor())
                    .slot(book.getSlot())
                    .build());
        }
        return bookDtos;
    }
}
