package com.example.AlatiMNS.repository;

import com.example.AlatiMNS.model.Ponuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PonudaRepository extends JpaRepository<Ponuda, Long> {

    @Query("SELECT o FROM Ponuda o WHERE o.trenutnaPonuda = (SELECT MAX(o2.trenutnaPonuda) FROM Ponuda o2)")
    Optional<Ponuda> findPonudaWithMaxTrenutnaVrednost();

    Optional<Ponuda> findPonudaByTrenutnaPonuda(Long trenutnaPonuda);

}
