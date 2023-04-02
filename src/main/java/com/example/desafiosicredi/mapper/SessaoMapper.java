package com.example.desafiosicredi.mapper;

import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.v1.response.SessaoResponse;

import java.time.format.DateTimeFormatter;

public class SessaoMapper {
    public static SessaoResponse toSessaoResponse(Pauta pauta){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return SessaoResponse.builder()
                .pautaId(pauta.getId())
                .inicio_sessao(formatter.format(pauta.getInicio_sessao()))
                .fim_sessao(formatter.format(pauta.getFim_sessao()))
                .build();
    }
}
