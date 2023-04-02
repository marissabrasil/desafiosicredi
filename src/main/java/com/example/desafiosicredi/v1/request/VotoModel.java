package com.example.desafiosicredi.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoModel {
    @NotNull(message = "O campo pautaId é obrigatório")
    private Long pautaId;
    @NotNull(message = "O campo assocId é obrigatório")
    private Long assocId;
    @NotBlank(message = "Voto não deve ser nulo")
    private String voto;
}
