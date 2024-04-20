package com.example.AlatiMNS.service;

import com.example.AlatiMNS.model.Ponuda;

import java.util.Optional;

public interface PonudaService {

    Optional<Ponuda> findMaxPonuda() throws Exception;
    Ponuda create (Ponuda ponuda) throws Exception;

    public Ponuda findPonudaByTrenutnaPonuda(Long trenutnaPonuda) throws Exception;

    void delete(Ponuda ponuda) throws Exception;
}
