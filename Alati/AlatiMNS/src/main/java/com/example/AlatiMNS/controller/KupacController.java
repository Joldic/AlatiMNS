package com.example.AlatiMNS.controller;


import com.example.AlatiMNS.model.Item;
import com.example.AlatiMNS.model.Kupac;
import com.example.AlatiMNS.model.Offer;
import com.example.AlatiMNS.model.dto.ItemDTO;
import com.example.AlatiMNS.model.dto.KupacDTO;
import com.example.AlatiMNS.model.dto.OfferDTO;
import com.example.AlatiMNS.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "api/kupci")
public class KupacController {

    private final KupacService kupacService;

    @Autowired
    public KupacController(KupacService kupacService){
        this.kupacService = kupacService;
    }

    @GetMapping(value = "/getKupac/{kupac}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KupacDTO> getItem(@PathVariable String kupac) throws Exception{
        Optional<Kupac> kupacName = this.kupacService.findKupacByKupac(kupac);

        KupacDTO kupacDTO = new KupacDTO();

        if(kupacName.isPresent()){
            kupacDTO = createDTOInstance(kupacName.get());
        }else{
            kupacDTO.setKupac("");
            kupacDTO.setPib("");
            kupacDTO.setSifra("");
            kupacDTO.setMesto("");
            kupacDTO.setAdresa("");
            kupacDTO.setAdresa("");
            kupacDTO.setTel("");
        }


        return new ResponseEntity<>(kupacDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllKupci", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KupacDTO>> getAllKupci(@RequestParam(required = false) String search){
        Page<Kupac> kupacPage;
        if(search != null && !search.isEmpty()){
            kupacPage = kupacService.findBySearchTerm(search, PageRequest.of(0, 10));
        }else{
            kupacPage = kupacService.findAll(PageRequest.of(0, 10));
        }

        List<KupacDTO> kupacDTOS = kupacPage.getContent().stream()
                .map(this::createDTOInstance)
                .collect(Collectors.toList());

        return new ResponseEntity<>(kupacDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/getSingleKupac", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KupacDTO>> getSingleKupac(@RequestParam(required = false) String search){
        Page<Kupac> kupacPage;
        if(search != null && !search.isEmpty() && search.length() > 2){
            kupacPage = kupacService.findBySearchTerm(search, PageRequest.of(0, 1));
        }else{
            kupacPage = kupacService.findAll(PageRequest.of(0, 0));
        }

        List<KupacDTO> kupacDTOS = kupacPage.getContent().stream()
                .map(this::createDTOInstance)
                .collect(Collectors.toList());

        return new ResponseEntity<>(kupacDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/createNewKupac", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KupacDTO> createKupac(@RequestBody KupacDTO kupacDTO) throws Exception{

        Kupac kupac = createKupacInstance(kupacDTO);
        Optional<Kupac> test = this.kupacService.findKupacByKupac(kupac.getKupac());

        if (test.isPresent()) {
            System.out.println("********************* " + test.get().getKupac());
            throw new Exception("Kupac sa tim imenom vec postoji");
        } else {
            System.out.println("Kupac not found.");
        }

        Kupac newKupac = this.kupacService.create(kupac);

        KupacDTO newKupacDTO = createDTOInstance(newKupac);

        return new ResponseEntity<>(newKupacDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteKupac/{id}")
    public ResponseEntity deleteKupac(@PathVariable Long id) throws Exception{
        Optional<Kupac> kupac = this.kupacService.findOne(id);
        this.kupacService.delete(kupac.get());

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updateCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KupacDTO> updateCustomer(@RequestBody KupacDTO kupacDTO) throws Exception{
        Optional<Kupac> kupac = this.kupacService.findOne(kupacDTO.getId());
        kupac.get().setKupac(kupacDTO.getKupac());
        kupac.get().setSifra(kupacDTO.getSifra());
        kupac.get().setMesto(kupacDTO.getMesto());
        kupac.get().setAdresa(kupacDTO.getAdresa());
        kupac.get().setPib(kupacDTO.getPib());
        kupac.get().setTel(kupacDTO.getTel());

        Kupac updatedKupac = this.kupacService.update(kupac.get());
        KupacDTO updatedKupacDTO = createDTOInstance(updatedKupac);

        return new ResponseEntity<>(updatedKupacDTO, HttpStatus.OK);
    }

    public Kupac createKupacInstance(KupacDTO kupacDTO){
        Kupac kupac = new Kupac();
        if(kupacDTO.getSifra() != null){
            kupac.setSifra(kupacDTO.getSifra());
        }else{
            kupac.setSifra("");
        }

        if(kupacDTO.getKupac() != null){
            kupac.setKupac(kupacDTO.getKupac());
        }else{
            kupac.setKupac("");
        }

        if(kupacDTO.getMesto() != null){
            kupac.setMesto(kupacDTO.getMesto());
        }else{
            kupac.setMesto("");
        }

        if(kupacDTO.getAdresa() != null){
            kupac.setAdresa(kupacDTO.getAdresa());
        }else{
            kupac.setAdresa("");
        }

        if(kupacDTO.getPib() != null){
            kupac.setPib(kupacDTO.getPib());
        }else{
            kupac.setPib("");
        }

        if(kupacDTO.getTel() != null){
            kupac.setTel(kupacDTO.getTel());
        }else{
            kupac.setTel("");
        }



        return kupac;
    }
    public KupacDTO createDTOInstance(Kupac kupac){
        KupacDTO ret_val = new KupacDTO(
                kupac.getId(),
                kupac.getSifra(),
                kupac.getKupac(),
                kupac.getMesto(),
                kupac.getAdresa(),
                kupac.getPib(),
                kupac.getTel()
        );
        return ret_val;
    }

}
