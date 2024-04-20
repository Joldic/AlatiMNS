package com.example.AlatiMNS.controller;

import com.example.AlatiMNS.model.Offer;
import com.example.AlatiMNS.model.Ponuda;
import com.example.AlatiMNS.model.dto.OfferDTO;
import com.example.AlatiMNS.model.dto.PonudaDTO;
import com.example.AlatiMNS.service.PonudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/ponude")
public class PonudaController {
    private final PonudaService ponudaService;

    @Autowired
    public PonudaController(PonudaService ponudaService){
        this.ponudaService = ponudaService;
    }

    @GetMapping(value = "/getPonuda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PonudaDTO> getPonuda() throws Exception{
        Optional<Ponuda> ponuda = this.ponudaService.findMaxPonuda();

        PonudaDTO ponudaDTO = createDTOInstance(ponuda.get());

        return new ResponseEntity<>(ponudaDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/createNewPonuda", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PonudaDTO> createPonuda(@RequestBody PonudaDTO ponudaDTO) throws Exception{
        Ponuda ponuda = new Ponuda(
                ponudaDTO.getTrenutnaPonuda()
        );

        Ponuda newPonuda = this.ponudaService.create(ponuda);

        PonudaDTO newPonudaDTO = createDTOInstance(newPonuda);

        return new ResponseEntity<>(newPonudaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{trenutnaPonuda}")
    public ResponseEntity<Void> deletePonuda(@PathVariable Long trenutnaPonuda) throws Exception{
        Ponuda ponuda = this.ponudaService.findPonudaByTrenutnaPonuda(trenutnaPonuda);
        this.ponudaService.delete(ponuda);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public PonudaDTO createDTOInstance(Ponuda ponuda){
        PonudaDTO ret_val = new PonudaDTO(
                ponuda.getId(),
                ponuda.getTrenutnaPonuda()
        );
        return ret_val;
    }
}
