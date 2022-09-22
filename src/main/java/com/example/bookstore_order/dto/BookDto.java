package com.example.bookstore_order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class BookDto {
    private String bookName;
    private int price;
    private String authorName;
    private int bookQuantity;
    private String Bookimage;
    private String bookDescription;
}
