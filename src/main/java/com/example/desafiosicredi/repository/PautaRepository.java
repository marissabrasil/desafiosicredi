package com.example.desafiosicredi.repository;

import com.example.desafiosicredi.enums.PautaStatus;
import com.example.desafiosicredi.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta,Long> {
    List<Pauta> findByStatus(PautaStatus status);
}
