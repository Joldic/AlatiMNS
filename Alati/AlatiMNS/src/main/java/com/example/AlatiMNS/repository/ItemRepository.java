package com.example.AlatiMNS.repository;

import com.example.AlatiMNS.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByKodBroj(String kodBroj);

    List<Item> findAll();

    //List<Item> findByKodBrojContainingIgnoreCaseOrEanKodContainingIgnoreCaseOrNazivContainingIgnoreCaseOrModelContainingIgnoreCase(String kodBroj, String eanKod, String naziv, String model);
    Page<Item> findByKodBrojContainingIgnoreCaseOrEanKodContainingIgnoreCaseOrNazivContainingIgnoreCaseOrModelContainingIgnoreCase(String kodBroj, String eanKod, String naziv, String model, Pageable pageable);
}
