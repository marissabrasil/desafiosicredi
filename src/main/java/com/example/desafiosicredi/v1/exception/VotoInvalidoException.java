package com.example.desafiosicredi.v1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VotoInvalidoException extends RuntimeException{

    private final HttpStatus status;

    public VotoInvalidoException(HttpStatus status){
        super("Voto inválido");
        this.status = status;
    }
}
