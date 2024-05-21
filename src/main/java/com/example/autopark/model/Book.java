package com.example.autopark.model;

import com.example.autopark.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book extends BaseEntity {
    private Integer floor;
    private Integer slot;
    private String licensePlate;
    private String name;
    private String phone;
    private Boolean isPayed;
}
