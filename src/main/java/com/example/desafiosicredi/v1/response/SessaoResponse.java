package com.example.desafiosicredi.v1.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SessaoResponse {
    private Long pautaId;
    private String inicio_sessao;
    private String fim_sessao;
}
