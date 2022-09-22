package com.example.bookstore_order.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object object;

    public ResponseDto(String string, String response) {
        this.message = string;
        this.object = response;
    }
    public ResponseDto(String s, Object response) {
        this.message=s;
        this.object=response;
    }
}
