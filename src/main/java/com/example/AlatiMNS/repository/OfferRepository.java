package com.example.AlatiMNS.repository;


import com.example.AlatiMNS.model.Offer;
import com.example.AlatiMNS.model.Ponuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findById(Long id);

    List<Offer> findAll();

    @Query("SELECT o FROM Offer o WHERE o.zavrseno = false")
    List<Offer> findOffersWithZavrsenoFalse();

    List<Offer> findOffersByPonuda(Ponuda ponuda);

}
