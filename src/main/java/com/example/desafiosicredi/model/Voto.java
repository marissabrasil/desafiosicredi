package com.example.desafiosicredi.model;

import com.example.desafiosicredi.enums.Resposta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "voto")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "associado_id", nullable = false)
    private Long assocId;
    @ManyToOne
    @JoinColumn(name="pauta_id", nullable=false)
    private Pauta pauta;
    @Column(name = "voto", nullable = false)
    private String voto;
}
