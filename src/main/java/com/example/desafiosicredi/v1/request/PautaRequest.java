package com.example.desafiosicredi.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PautaRequest {
    @NotBlank(message = "O campo assunto é obrigatório")
    private String assunto;
}
