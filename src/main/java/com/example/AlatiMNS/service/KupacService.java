package com.example.AlatiMNS.service;

import com.example.AlatiMNS.model.Kupac;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KupacService {

    Page<Kupac> findAll(Pageable pageable);

    Page<Kupac> findBySearchTerm(String searchTerm, Pageable pageable);

    Optional<Kupac> findKupacByKupac(String kupac) throws Exception;

    Kupac create(Kupac kupac) throws Exception;

    Optional<Kupac> findOne(Long id) throws Exception;

    void delete(Kupac kupac) throws Exception;

    Kupac update(Kupac kupac) throws Exception;
}
