package com.example.AlatiMNS.repository;

import com.example.AlatiMNS.model.Kupac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KupacRepository  extends JpaRepository<Kupac, Long> {
    Optional<Kupac> findKupacByKupac(String kupac);

    List<Kupac> findAll();

    Page<Kupac> findByKupacContainingIgnoreCase(String kupac, Pageable pageable);

}
