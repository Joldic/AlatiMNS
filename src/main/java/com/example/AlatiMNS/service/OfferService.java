package com.example.AlatiMNS.service;

import com.example.AlatiMNS.model.Offer;
import com.example.AlatiMNS.model.dto.Obracun;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> findAll();

    void delete(Offer offer) throws Exception;

    Obracun clearOffers();

    Offer update(Offer offer) throws Exception;

    Optional<Offer> findOne(Long id) throws Exception;

    Offer create(Offer offer) throws Exception;

    Offer addCustomOffer(Offer offer, Long trenutnaPonuda) throws Exception;

    List<Offer> findOffersByTrenutnaPonuda(Long trenutnaPonuda);

    List<Offer> findOffersByZavrsenoFalse();

    List<Offer> findFreshOffers();
}
