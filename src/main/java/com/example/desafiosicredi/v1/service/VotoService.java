package com.example.desafiosicredi.v1.service;

import com.example.desafiosicredi.enums.PautaStatus;
import com.example.desafiosicredi.v1.exception.*;
import com.example.desafiosicredi.mapper.VotoMapper;
import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.model.Voto;
import com.example.desafiosicredi.repository.PautaRepository;
import com.example.desafiosicredi.repository.VotoRepository;
import com.example.desafiosicredi.v1.request.VotoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.example.desafiosicredi.enums.Resposta.NAO;
import static com.example.desafiosicredi.enums.Resposta.SIM;

@Service
public class VotoService {
    @Autowired
    VotoRepository votoRepository;
    @Autowired
    PautaRepository pautaRepository;

    public Voto votar(VotoModel votoRequest){
        Pauta pauta = pautaRepository.findById(votoRequest.getPautaId())
                .orElseThrow(() -> new PautaNaoEncontradaException(HttpStatus.NOT_FOUND));

        if(!(votoRequest.getVoto().equals(NAO) || votoRequest.getVoto().equals(SIM))){
            throw new VotoInvalidoException(HttpStatus.BAD_REQUEST);
        }

        if (pauta.getStatus() != PautaStatus.ABERTA){
            throw new PautaFechadaException(HttpStatus.FORBIDDEN);
        }

        Voto voto = votoRepository.findByAssocIdAndPauta(votoRequest.getAssocId(), pauta);
        if(voto != null){
            throw new AssociadoJaVotouException(HttpStatus.FORBIDDEN);
        }

        return votoRepository.save(VotoMapper.toVoto(votoRequest, pauta));
    }
}
