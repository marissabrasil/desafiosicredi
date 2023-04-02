package com.example.desafiosicredi.v1.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessaoRequest {
    @NotNull(message = "O campo pautaId é obrigatório")
    private Long pautaId;
    private int duracao = 1;
}
