package com.example.bookstore_order.service;

import com.example.bookstore_order.dto.OrderDto;
import com.example.bookstore_order.model.Order;

import java.util.List;

public interface IOrderService {
    String addOrder(OrderDto orderDto);

    List<Order> findAll();

    Order FindById(Long id);

    String deleteById(Long userId,Long id);



    String updateOrderData(Long id, OrderDto orderDto);
}