package com.example.AlatiMNS.service.impl;

import com.example.AlatiMNS.model.Item;
import com.example.AlatiMNS.repository.ItemRepository;
import com.example.AlatiMNS.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public Optional<Item> findItemByKodBroj(String kodBroj) throws Exception{
        Optional<Item> item = this.itemRepository.findByKodBroj(kodBroj);
        if(item == null){
            throw new Exception("Item with this is does not exist!");
        }

        return item;
    }

//    @Override
//    public  List<Item> findBySearchTerm(String searchItem){
//        String kodBroj = "";
//        String eanKod = "";
//        String naziv = "";
//        String model = "";
//        return itemRepository.findByKodBrojContainingIgnoreCaseOrEanKodContainingIgnoreCaseOrNazivContainingIgnoreCaseOrModelContainingIgnoreCase(searchItem, searchItem, searchItem, searchItem);
//    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
    @Override
    public Page<Item> findBySearchTerm(String searchTerm, Pageable pageable) {
        return itemRepository.findByKodBrojContainingIgnoreCaseOrEanKodContainingIgnoreCaseOrNazivContainingIgnoreCaseOrModelContainingIgnoreCase(searchTerm, searchTerm, searchTerm, searchTerm, pageable);
    }

    @Override
    public Optional<Item> findOne(Long id) throws Exception{
        Optional<Item> item = this.itemRepository.findById(id);
        if(!item.isPresent()){
            throw new Exception("Item with this id does not exist!");
        }
        return item;
    }

    @Override
    public Item create(Item item) throws Exception{
        if(item.getId() != null){
            throw new Exception("Item with this id already exist!");
        }
        return this.itemRepository.save(item);
    }

    @Override
    public Item update(Item item) throws Exception{
        return this.itemRepository.save(item);
    }
}
