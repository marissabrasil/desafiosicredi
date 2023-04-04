package com.example.desafiosicredi.v1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AssociadoJaVotouException extends RuntimeException{
    private final HttpStatus status;

    public AssociadoJaVotouException(HttpStatus status){
        super("Associado já votou na sessão da pauta.");
        this.status = status;
    }
}
