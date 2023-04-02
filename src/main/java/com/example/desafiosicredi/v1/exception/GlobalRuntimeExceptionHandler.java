package com.example.desafiosicredi.v1.exception;

import lombok.Getter;

@Getter
public class GlobalRuntimeExceptionHandler extends RuntimeException{
    public GlobalRuntimeExceptionHandler(String message){
        super(message);
    }
}
