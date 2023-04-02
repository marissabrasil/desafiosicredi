package com.example.desafiosicredi.v1.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ResultadoResponse {
    private Long pautaId;
    private String assunto;
    private Long qtdSim;
    private  Long qtdNao;

}
