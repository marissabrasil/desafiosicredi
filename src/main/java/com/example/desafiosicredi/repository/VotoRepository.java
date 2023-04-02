package com.example.desafiosicredi.repository;

import com.example.desafiosicredi.model.Pauta;
import com.example.desafiosicredi.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto,Long> {

    Voto findByAssocIdAndPauta(Long assocId, Pauta pauta);
}
