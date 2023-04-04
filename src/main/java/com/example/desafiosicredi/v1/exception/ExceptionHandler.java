package com.example.desafiosicredi.v1.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException e){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("time", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        body.put("messages", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PautaFechadaException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse pautaFechada(PautaFechadaException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .time(LocalDateTime.now())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PautaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse pautaNaoEncontrada(PautaNaoEncontradaException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .time(LocalDateTime.now())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(VotoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse votoInvalido(VotoInvalidoException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .time(LocalDateTime.now())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AssociadoJaVotouException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse associadoJaVotou(AssociadoJaVotouException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .time(LocalDateTime.now())
                .build();
    }
}
