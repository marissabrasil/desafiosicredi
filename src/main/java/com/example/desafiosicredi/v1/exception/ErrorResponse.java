package com.example.desafiosicredi.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@ToString
@Data
public class ErrorResponse {
    String message;
    HttpStatus status;
    LocalDateTime time;
    List<String> errors;

}
