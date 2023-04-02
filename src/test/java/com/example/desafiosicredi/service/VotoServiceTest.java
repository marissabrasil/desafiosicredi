package com.example.desafiosicredi.service;

import com.example.desafiosicredi.enums.PautaStatus;
import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.model.Voto;
import com.example.desafiosicredi.repository.PautaRepository;
import com.example.desafiosicredi.repository.VotoRepository;
import com.example.desafiosicredi.v1.request.VotoModel;
import com.example.desafiosicredi.v1.service.VotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VotoServiceTest {
    @InjectMocks
    VotoService votoService;
    @Mock
    VotoRepository votoRepository;
    @Mock
    PautaRepository pautaRepository;

    @Test
    public void shouldVotar(){
        Pauta pauta = Pauta.builder()
                .id(1L)
                .assunto("teste")
                .status(PautaStatus.ABERTA)
                .build();
        Voto voto = new Voto(1L, 1L, pauta, "Sim");
        VotoModel votoModel = VotoModel.builder()
                .pautaId(1L)
                .assocId(1L)
                .voto("Sim")
                .build();

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
        when(votoRepository.findByAssocIdAndPauta(any(), any(Pauta.class))).thenReturn(null);
        when(votoRepository.save(any(Voto.class))).thenReturn(voto);

        Voto salvo = votoService.votar(votoModel);
        assertEquals(votoModel.getVoto(), salvo.getVoto());
        assertEquals(votoModel.getAssocId(), salvo.getAssocId());
        assertEquals(votoModel.getPautaId(), salvo.getPauta().getId());
    }
}
