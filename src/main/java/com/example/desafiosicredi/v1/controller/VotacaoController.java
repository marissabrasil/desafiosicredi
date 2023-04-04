package com.example.desafiosicredi.v1.controller;

import com.example.desafiosicredi.mapper.PautaMapper;
import com.example.desafiosicredi.mapper.VotoMapper;
import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.model.Voto;
import com.example.desafiosicredi.v1.request.PautaRequest;
import com.example.desafiosicredi.v1.request.SessaoRequest;
import com.example.desafiosicredi.v1.request.VotoModel;
import com.example.desafiosicredi.v1.response.PautaResponse;
import com.example.desafiosicredi.v1.response.ResultadoResponse;
import com.example.desafiosicredi.v1.response.SessaoResponse;
import com.example.desafiosicredi.v1.service.PautaService;
import com.example.desafiosicredi.v1.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class VotacaoController {
    @Autowired
    PautaService pautaService;
    @Autowired
    VotoService votoService;

    @GetMapping("/pautas")
    public ResponseEntity<List<Pauta>> getPautas(){
        return new ResponseEntity<List<Pauta>>(pautaService.getPautas(), HttpStatus.OK);
    }

    @PostMapping("/cadastro-pauta")
    public ResponseEntity<PautaResponse> cadastarPauta(@Valid @RequestBody PautaRequest pautaRequest){
        Pauta pauta = pautaService.cadastrarPauta(pautaRequest);
        return new ResponseEntity<PautaResponse>(PautaMapper.toResponse(pauta), HttpStatus.CREATED);
    }

    @GetMapping("/resultado/{id}")
    public ResponseEntity<ResultadoResponse> getResultadoPauta(@PathVariable("id") Long id){
        return new ResponseEntity<ResultadoResponse> (pautaService.getResultadoPauta(id), HttpStatus.OK);
    }

    @PostMapping("/abrir-sessao")
    public ResponseEntity<SessaoResponse> abrirSessao(@Valid @RequestBody SessaoRequest sessaoRequest){
        return new ResponseEntity<SessaoResponse> (pautaService.abrirSessao(sessaoRequest), HttpStatus.CREATED);
    }

    @PostMapping("/votar")
    public ResponseEntity<VotoModel> votar(@Valid @RequestBody VotoModel votoRequest){
        Voto voto = votoService.votar(votoRequest);
        return new ResponseEntity<VotoModel> (VotoMapper.toVotoModel(voto), HttpStatus.CREATED);
    }
}
