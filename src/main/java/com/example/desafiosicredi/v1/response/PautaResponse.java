package com.example.desafiosicredi.v1.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PautaResponse {

    private Long pautaId;
    private String assunto;
}
