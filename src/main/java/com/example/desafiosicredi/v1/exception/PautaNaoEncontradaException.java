package com.example.desafiosicredi.v1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PautaNaoEncontradaException extends RuntimeException{
    private final HttpStatus status;

    public PautaNaoEncontradaException(HttpStatus status){
        super("Pauta não encontrada");
        this.status = status;
    }

}
