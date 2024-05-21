package com.example.autopark.dto.book;

import com.example.autopark.model.Book;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class PayDto {
    private UUID bookId;
    private Integer floor;
    private Integer slot;
    private String licensePlate;
    private String name;
    private String phone;
    private Integer price;
    private Integer roundedHours;
    private Date endTime;
    private Date createdAt;

    public void setRoundedHours(Integer roundedHours) {
        this.roundedHours = roundedHours;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static PayDto toDto(Book book) {
        return PayDto
                .builder()
                .floor(book.getFloor())
                .slot(book.getSlot())
                .licensePlate(book.getLicensePlate())
                .name(book.getName())
                .phone(book.getPhone())
                .createdAt(book.getCreatedAt())
                .endTime(new Date())
                .build();
    }

}
