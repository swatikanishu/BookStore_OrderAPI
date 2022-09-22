package com.example.bookstore_order.service;
import com.example.bookstore_order.dto.BookDto;
import com.example.bookstore_order.dto.OrderDto;
import com.example.bookstore_order.dto.UserDto;
import com.example.bookstore_order.exception.OrderException;
import com.example.bookstore_order.model.Order;
import com.example.bookstore_order.repo.OrderRepo;
import com.example.bookstore_order.utility.EmailSenderService;
import com.example.bookstore_order.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements  IOrderService {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String addOrder(OrderDto orderDto) {
        UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + orderDto.getUserid(), UserDto.class);
        BookDto bookDetails = restTemplate.getForObject("http://localhost:8082/Book/get/" + orderDto.getBookId(), BookDto.class);
        if (userData.equals(null) && bookDetails.equals(null)) {
            throw new OrderException("invalid user Id and BookID");
        } else {
            Order orderDetails = new Order(orderDto);
            String token = tokenUtil.createToken(orderDetails.getOrderID());
            emailSenderService.sendEmail(userData.getEmail_address(), "Added Your Details", "http://localhost:8081/User/retrieve/" + token);
            return token;
        }
    }


    @Override
    public List<Order> findAll() {
        List<Order> orderdetails = orderRepo.findAll();
        return orderdetails;
    }

    @Override
    public Order FindById(Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            return order;

        } else
            throw new OrderException("order id is not found");
    }

    @Override
    public String deleteById(Long userId,Long id) {

        Order findById = orderRepo.findById(id).orElse(null);
        UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + userId, UserDto.class);

        if (findById != null&& userData!=null) {
           if(userData.getUserid().equals(findById.getUserid())) {
               orderRepo.deleteById(id);
               return "data is deleted";
           }else
               throw new OrderException("user id does not match");

        } else throw new OrderException("Order id is  invalid");

    }

    @Override
    public String updateOrderData(Long id, OrderDto orderDto) {
        UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + orderDto.getUserid(), UserDto.class);
        BookDto bookDetails = restTemplate.getForObject("http://localhost:8082/Book/get/" + orderDto.getBookId(), BookDto.class);
        Order editdata = orderRepo.findById(id).orElse(null);
        if (userData!=null && bookDetails!=null && editdata!=null){
            editdata.setBookId(orderDto.getBookId());
            editdata.setUserid(orderDto.getUserid());
            editdata.setQuantity(orderDto.getQuantity());
            String token = tokenUtil.createToken(editdata.getOrderID());
           emailSenderService.sendEmail(userData.getEmail_address(), "Added Your Details", "http://localhost:8081/User/retrieve/" + token);
            return token;
        }

        else {

            throw new OrderException(" id is invalid");
        }
    }
}




