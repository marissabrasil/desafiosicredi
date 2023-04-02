package com.example.desafiosicredi.mapper;

import com.example.desafiosicredi.enums.PautaStatus;
import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.v1.request.PautaRequest;
import com.example.desafiosicredi.v1.response.PautaResponse;

public class PautaMapper {

    public static Pauta toEntity(PautaRequest pautaRequest){
        return Pauta.builder()
                .assunto(pautaRequest.getAssunto())
                .status(PautaStatus.CRIADA)
                .build();
    }

    public static PautaResponse toResponse(Pauta pauta){
        return PautaResponse.builder()
                .pautaId(pauta.getId())
                .assunto(pauta.getAssunto())
                .build();
    }
}
