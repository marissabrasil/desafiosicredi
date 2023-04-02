package com.example.desafiosicredi.mapper;

import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.model.Voto;
import com.example.desafiosicredi.v1.request.VotoModel;

public class VotoMapper {

    public static Voto toVoto(VotoModel votoRequest, Pauta pauta){
        return Voto.builder()
                .assocId(votoRequest.getAssocId())
                //.answer((request.getAnswer().equals("Sim")) ? Answer.YES : Answer.NO)
                .pauta(pauta)
                .voto(votoRequest.getVoto())
                .build();
    }

    public static VotoModel toVotoModel(Voto voto){
        return VotoModel.builder()
                .pautaId(voto.getPauta().getId())
                .assocId(voto.getAssocId())
                .voto(voto.getVoto())
                .build();
    }
}
