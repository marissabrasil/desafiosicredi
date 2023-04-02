package com.example.desafiosicredi.mapper;

import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.v1.response.ResultadoResponse;

public class ResultadoMapper {
    public static ResultadoResponse toResponse(Pauta pauta, Long qtdSim, Long qtdNao){
        return ResultadoResponse.builder()
                .pautaId(pauta.getId())
                .assunto(pauta.getAssunto())
                .qtdNao(qtdNao)
                .qtdSim(qtdSim)
                .build();
    }
}
