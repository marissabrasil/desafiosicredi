package com.example.desafiosicredi.service;

import com.example.desafiosicredi.enums.PautaStatus;
import com.example.desafiosicredi.mapper.SessaoMapper;
import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.model.Voto;
import com.example.desafiosicredi.repository.PautaRepository;
import com.example.desafiosicredi.v1.request.PautaRequest;
import com.example.desafiosicredi.v1.request.SessaoRequest;
import com.example.desafiosicredi.v1.response.ResultadoResponse;
import com.example.desafiosicredi.v1.response.SessaoResponse;
import com.example.desafiosicredi.v1.service.PautaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {
    @InjectMocks
    PautaService pautaService;
    @Mock
    PautaRepository pautaRepository;

    @Test
    public void shouldCadastrarPauta(){
        PautaRequest pautaRequest = new PautaRequest("teste");
        Pauta pauta = Pauta.builder()
                .id(1L)
                .assunto("teste")
                .status(PautaStatus.CRIADA)
                .build();

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);
        Pauta salva = pautaService.cadastrarPauta(pautaRequest);
        assertEquals(pauta, salva);
    }

    @Test
    public void shouldRetornarPautas(){
        Pauta pauta = Pauta.builder()
                .id(1L)
                .assunto("teste")
                .status(PautaStatus.CRIADA)
                .build();
        List<Pauta> lista = List.of(pauta);

        when(pautaRepository.findAll()).thenReturn(lista);
        List<Pauta> encontradas = pautaService.getPautas();
        assertEquals(lista, encontradas);

    }

    @Test
    public void shouldAbrirSessao(){
        SessaoRequest sessaoRequest = new SessaoRequest(1L,2);
        Pauta pautacriada = Pauta.builder()
                .id(1L)
                .assunto("teste")
                .status(PautaStatus.CRIADA)
                .inicio_sessao(LocalDateTime.now())
                .fim_sessao(LocalDateTime.now().plusMinutes(2))
                .build();
        Pauta pautaaberta = Pauta.builder()
                .id(1L)
                .assunto("teste")
                .status(PautaStatus.ABERTA)
                .inicio_sessao(pautacriada.getInicio_sessao())
                .fim_sessao(pautacriada.getFim_sessao())
                .build();
        SessaoResponse sessaoResponse = SessaoMapper.toSessaoResponse(pautaaberta);

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pautacriada));
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pautaaberta);
        SessaoResponse salva = pautaService.abrirSessao(sessaoRequest);

        assertEquals(salva.getPautaId(), sessaoResponse.getPautaId());
        assertEquals(salva.getInicio_sessao(), sessaoResponse.getInicio_sessao());
        assertEquals(salva.getFim_sessao(), sessaoResponse.getFim_sessao());
    }

    @Test
    public void shouldRetornarResultadoPauta(){
        Pauta pauta = Pauta.builder()
                .id(1L)
                .assunto("teste")
                .build();
        Voto voto = new Voto(1L, 1L, pauta, "Sim");
        pauta.setVotos(List.of(voto));
        ResultadoResponse resultadoResponse = ResultadoResponse.builder()
                .pautaId(1L)
                .assunto("teste")
                .qtdNao(0L)
                .qtdSim(1L)
                .build();

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
        ResultadoResponse salvo = pautaService.getResultadoPauta(1L);

        assertEquals(salvo.getPautaId(), resultadoResponse.getPautaId());
        assertEquals(salvo.getAssunto(), resultadoResponse.getAssunto());
        assertEquals(salvo.getQtdNao(), resultadoResponse.getQtdNao());
        assertEquals(salvo.getQtdSim(), resultadoResponse.getQtdSim());
    }
}
