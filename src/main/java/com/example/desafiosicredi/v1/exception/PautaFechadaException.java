package com.example.desafiosicredi.v1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class PautaFechadaException extends RuntimeException{
    private final HttpStatus status;

    public PautaFechadaException(HttpStatus status){
        super("Sessão da pauta fechada ou não criada");
        this.status = status;
    }
}
