package com.example.bookstore_order.model;

import com.example.bookstore_order.dto.OrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="book_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderID", nullable = false)
    Long orderID;
    LocalDate date = LocalDate.now();
    int price;
    int quantity;
    String address;

    Long userid;
    Long  bookId;
    boolean cancel;

    public Order(OrderDto orderDto) {
        this.date = orderDto.getDate();
        this.price = orderDto.getPrice();
        this.quantity =orderDto.getQuantity();
        this.address = orderDto.getAddress();
        this.userid = orderDto.getUserid();
        this.bookId =orderDto.getBookId();
        this.cancel = orderDto.isCancel();

    }




}
