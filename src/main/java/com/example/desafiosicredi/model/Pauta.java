package com.example.desafiosicredi.model;

import com.example.desafiosicredi.enums.PautaStatus;
import com.example.desafiosicredi.v1.request.SessaoRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "assunto", nullable = false)
    private String assunto;
    @Column(name = "inicio_sessao", nullable = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime inicio_sessao;
    @Column(name = "fim_sessao", nullable = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime fim_sessao;
    @Column(name = "status", nullable = true)
    private PautaStatus status = PautaStatus.CRIADA;
    @JsonIgnore
    @OneToMany(mappedBy="pauta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Voto> votos;

    public void abrirSessao(SessaoRequest sessaoRequest){
        this.status = PautaStatus.ABERTA;
        this.inicio_sessao = LocalDateTime.now();
        this.fim_sessao = this.inicio_sessao.plusMinutes(sessaoRequest.getDuracao());
    }

}
