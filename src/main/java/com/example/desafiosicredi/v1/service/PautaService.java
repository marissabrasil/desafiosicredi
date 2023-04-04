package com.example.desafiosicredi.v1.service;

import com.example.desafiosicredi.enums.PautaStatus;
import com.example.desafiosicredi.v1.exception.GlobalRuntimeExceptionHandler;
import com.example.desafiosicredi.kafka.KafkaProducer;
import com.example.desafiosicredi.mapper.PautaMapper;
import com.example.desafiosicredi.mapper.ResultadoMapper;
import com.example.desafiosicredi.mapper.SessaoMapper;
import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.model.Voto;
import com.example.desafiosicredi.repository.PautaRepository;
import com.example.desafiosicredi.v1.request.PautaRequest;
import com.example.desafiosicredi.v1.request.SessaoRequest;
import com.example.desafiosicredi.v1.response.ResultadoResponse;
import com.example.desafiosicredi.v1.response.SessaoResponse;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;

import static com.example.desafiosicredi.enums.Resposta.NAO;
import static com.example.desafiosicredi.enums.Resposta.SIM;

@Service
@AllArgsConstructor
public class PautaService {
    @Autowired
    private PautaRepository pautaRepository;
    KafkaProducer kafkaProducer;
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PautaService.class);

    public List<Pauta> getPautas(){
        return pautaRepository.findAll();
    }

    public Pauta cadastrarPauta(PautaRequest pautaRequest){
        return pautaRepository.save(PautaMapper.toEntity(pautaRequest));
    }

    public SessaoResponse abrirSessao(SessaoRequest sessaoRequest){
        Pauta pauta = pautaRepository.findById(sessaoRequest.getPautaId())
                .orElseThrow(() -> new GlobalRuntimeExceptionHandler("Pauta não encontrada"));
        if(pauta.getStatus() == PautaStatus.CRIADA){
            pauta.abrirSessao(sessaoRequest);
        }else{
            throw new GlobalRuntimeExceptionHandler("Sessão da pauta fechada.");
        }

        return SessaoMapper.toSessaoResponse(pautaRepository.save(pauta));
    }

    public ResultadoResponse getResultadoPauta(Long id){
        Pauta pauta = pautaRepository.findById(id)
                .orElseThrow(() -> new GlobalRuntimeExceptionHandler("Pauta não encontrada"));
        return obterResultado(pauta);
    }

    @Scheduled(fixedDelay = 10000)
    public void enviarResultados(){
        pautaRepository.findByStatus(PautaStatus.ABERTA).stream()
                .filter(p -> p.getFim_sessao().isBefore(LocalDateTime.now()))
                .forEach(pauta -> {
                    LOGGER.info("Atualizando status da pauta: "+pauta.getId());
                    atualizarPauta(pauta);

                    ResultadoResponse resultadoResponse = obterResultado(pauta);
                    LOGGER.info("Enviando resultado: "+resultadoResponse);
                    kafkaProducer.send(resultadoResponse.toString());
                });

    }

    private ResultadoResponse obterResultado(Pauta pauta){
        List<Voto> votos = pauta.getVotos();
        Long qtdSim = votos.stream()
                .filter(voto -> voto.getVoto().equals(SIM)).count();
        Long qtdNao = votos.stream()
                .filter(voto -> voto.getVoto().equals(NAO)).count();

        return ResultadoMapper.toResponse(pauta, qtdSim, qtdNao);
    }

    private void atualizarPauta(Pauta pauta){
        pauta.setStatus(PautaStatus.FECHADA);
        pautaRepository.save(pauta);
    }
}
