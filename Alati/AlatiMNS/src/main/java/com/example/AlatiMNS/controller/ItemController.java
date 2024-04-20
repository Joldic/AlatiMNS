package com.example.AlatiMNS.controller;

import com.example.AlatiMNS.model.Item;
import com.example.AlatiMNS.model.dto.ItemDTO;
import com.example.AlatiMNS.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping(value = "/getItem/{kodBroj}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> getItem(@PathVariable String kodBroj) throws Exception{
        Optional<Item> item = this.itemService.findItemByKodBroj(kodBroj);

        ItemDTO itemDTO = createDTOInstance(item.get());

        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

//    @GetMapping(value = "/getAllItems", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ItemDTO>> getAllItems(@RequestParam(required = false) String search) {
//        List<Item> items;
//        if (search != null && !search.isEmpty()) {
//            items = this.itemService.findBySearchTerm(search);
//        } else {
//            items = this.itemService.findAll();
//        }
//
//        List<ItemDTO> itemDTOS = items.stream()
//                .map(this::createDTOInstance)
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(itemDTOS, HttpStatus.OK);
//    }

    @GetMapping(value = "/getAllItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> getAllItems(@RequestParam(required = false) String search) {
        Page<Item> itemsPage;
        if (search != null && !search.isEmpty()) {
            itemsPage = itemService.findBySearchTerm(search, PageRequest.of(0, 10));
        } else {
            itemsPage = itemService.findAll(PageRequest.of(0, 10));
        }

        List<ItemDTO> itemDTOS = itemsPage.getContent().stream()
                .map(this::createDTOInstance)
                .collect(Collectors.toList());

        return new ResponseEntity<>(itemDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/updateItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO) throws Exception{
        Optional<Item> item = this.itemService.findOne(itemDTO.getId());
        item.get().setCena(itemDTO.getCena());
        item.get().setNaziv(itemDTO.getNaziv());
        item.get().setModel(itemDTO.getModel());
        item.get().setEanKod(itemDTO.getEanKod());
        item.get().setKodBroj(itemDTO.getKodBroj());

        Item updatedItem = this.itemService.update(item.get());
        ItemDTO updatedItemDTO = createDTOInstance(updatedItem);

        return new ResponseEntity<>(updatedItemDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/createNewItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) throws Exception{
        Item item = createItemInstance(itemDTO);

        Item newItem = this.itemService.create(item);

        ItemDTO newItemDTO = createDTOInstance(newItem);

        return new ResponseEntity<>(newItemDTO, HttpStatus.CREATED);
    }

    public ItemDTO createDTOInstance(Item item){
        ItemDTO ret_val = new ItemDTO(
                item.getId(),
                item.getKodBroj(),
                item.getModel(),
                item.getNaziv(),
                item.getEanKod(),
                item.getCena()
        );
        return ret_val;
    }

    public Item createItemInstance(ItemDTO itemDTO){
        Item item = new Item();

        item.setCena(itemDTO.getCena());
        item.setKodBroj(itemDTO.getKodBroj());
        item.setEanKod(itemDTO.getEanKod());
        item.setModel(itemDTO.getModel());
        item.setNaziv(itemDTO.getNaziv());

        return item;
    }
}


