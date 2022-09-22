package com.example.bookstore_order.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDto {
    int quantity;
    String address;
    Long userid;
    Long bookId;
    LocalDate Date=LocalDate.now();
    boolean cancel;
    int price;


}




