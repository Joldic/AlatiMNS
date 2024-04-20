package com.example.AlatiMNS.service;

import com.example.AlatiMNS.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAll();

    Optional<Item> findItemByKodBroj(String kodBroj) throws Exception;

    //List<Item> findBySearchTerm(String searchItem);

    Page<Item> findAll(Pageable pageable);


     Page<Item> findBySearchTerm(String searchTerm, Pageable pageable);

     Optional<Item> findOne(Long id) throws Exception;

     Item create(Item item) throws Exception;

     Item update(Item item) throws Exception;
}
