package com.example.AlatiMNS.controller;


import com.example.AlatiMNS.model.Offer;
import com.example.AlatiMNS.model.Ponuda;
import com.example.AlatiMNS.model.dto.ItemDTO;
import com.example.AlatiMNS.model.dto.Obracun;
import com.example.AlatiMNS.model.dto.OfferDTO;
import com.example.AlatiMNS.service.OfferService;
import com.example.AlatiMNS.service.PonudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("api/offers")
public class OfferController {
    private final OfferService offerService;

    private final PonudaService ponudaService;

    @Autowired
    public OfferController(OfferService offerService, PonudaService ponudaService){

        this.offerService = offerService;
        this.ponudaService = ponudaService;
    }

    @GetMapping(value = "/getOffer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDTO> getOffer(@PathVariable Long id) throws Exception{
        Optional<Offer> offer = this.offerService.findOne(id);

        OfferDTO offerDTO = createDTOInstance(offer.get());

        return new ResponseEntity<>(offerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferDTO>> getAllOffers(){
        List<Offer> offers = this.offerService.findAll();

        List<OfferDTO> offerDTOS = new ArrayList<OfferDTO>();

        for(Offer f : offers){
            OfferDTO offerDTO = createDTOInstance2(f);
            offerDTOS.add(offerDTO);
        }

        return new ResponseEntity<>(offerDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOffersByZavrsenoFalse/{trenutnaPonuda}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferDTO>> getAllOffersByZavrsenoFalse(@PathVariable Long trenutnaPonuda){
        List<Offer> offers = this.offerService.findOffersByTrenutnaPonuda(trenutnaPonuda);

        List<OfferDTO> offerDTOS = new ArrayList<OfferDTO>();

        for(Offer f : offers){
            if(f.getZavrseno() == false) {
                OfferDTO offerDTO = createDTOInstance2(f);
                offerDTOS.add(offerDTO);
            }
        }

        return new ResponseEntity<>(offerDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/getOffersByTrenutnaPonuda/{trenutnaPonuda}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferDTO>> getAllOffersByTrenutnaPonuda(@PathVariable Long trenutnaPonuda){
        List<Offer> offers = this.offerService.findOffersByTrenutnaPonuda(trenutnaPonuda);

        List<OfferDTO> offerDTOS = new ArrayList<OfferDTO>();

        for(Offer f : offers){
            OfferDTO offerDTO = createDTOInstance2(f);
            offerDTOS.add(offerDTO);
        }

        return new ResponseEntity<>(offerDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/getFreshOffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferDTO>> getFreshOffers(){
        List<Offer> offers = this.offerService.findFreshOffers();

        List<OfferDTO> offerDTOS = new ArrayList<OfferDTO>();

        for(Offer f : offers){
            OfferDTO offerDTO = createDTOInstance2(f);
            offerDTOS.add(offerDTO);
        }

        return new ResponseEntity<>(offerDTOS, HttpStatus.OK);
    }



    @PostMapping(value = "/createNewOffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDTO> createOffer(@RequestBody ItemDTO itemDTO) throws Exception{
        Offer offer = new Offer();
        offer.setSifraArtikla(itemDTO.getKodBroj());
        offer.setOpisArtikla(itemDTO.getNaziv());
        offer.setJedinicaMere("KOM");
        offer.setKolicina(itemDTO.getKolicina());
        offer.setVpc(itemDTO.getCena());
        Float iznos = itemDTO.getKolicina() * itemDTO.getCena();
        offer.setIznos(iznos); //broj komada puta cena
        offer.setRabat(itemDTO.getRabat());
        Float ukupno = (float) (iznos * (1.0 - itemDTO.getRabat()/100.0));
        offer.setUkupno(ukupno);
        Float pdv = 0.2F;
        Float iznosPdv = pdv*ukupno;
        offer.setIznosPdv(iznosPdv);
        offer.setFakturnaVrednost(ukupno + iznosPdv);
        offer.setZavrseno(false);

        Offer newOffer = this.offerService.create(offer);

        OfferDTO newOfferDTO = createDTOInstance(newOffer);

        return new ResponseEntity<>(newOfferDTO, HttpStatus.CREATED);
    }

    public static String generateNumbers() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(10); // Generates a random number between 0 and 9
            sb.append(randomNumber);
        }
        
        return sb.toString();
    }

    @PostMapping(value = "/createNewCustomOffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) throws Exception{
        Offer offer = new Offer();
        offer.setSifraArtikla(generateNumbers());
        offer.setOpisArtikla(offerDTO.getOpisArtikla());
        offer.setJedinicaMere(offerDTO.getJedinicaMere());
        offer.setKolicina(offerDTO.getKolicina());
        offer.setVpc(offerDTO.getVpc());
        Float iznos = offerDTO.getKolicina() * offerDTO.getVpc();
        offer.setIznos(iznos); //broj komada puta cena
        offer.setRabat(offerDTO.getRabat());
        Float ukupno = (float) (iznos * (1.0 - offerDTO.getRabat()/100.0));
        offer.setUkupno(ukupno);
        Float pdv = 0.2F;
        Float iznosPdv = pdv*ukupno;
        offer.setIznosPdv(iznosPdv);
        offer.setFakturnaVrednost(ukupno + iznosPdv);
        offer.setZavrseno(false);

        Offer newOffer = this.offerService.create(offer);

        OfferDTO newOfferDTO = createDTOInstance(newOffer);

        return new ResponseEntity<>(newOfferDTO, HttpStatus.CREATED);
    }


    @PostMapping(value = "/createCustomOffer/{trenutnaPonuda}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDTO> addCustomOffer(@RequestBody ItemDTO itemDTO, @PathVariable Long trenutnaPonuda) throws Exception{
        Offer offer = new Offer();
        offer.setSifraArtikla(generateNumbers());
        if(itemDTO.getNaziv() != null){
            offer.setOpisArtikla(itemDTO.getNaziv());
        }else{
            offer.setOpisArtikla("");
        }
        offer.setJedinicaMere("KOM");

        Long kolicina = 0L;
        if(itemDTO.getKolicina() != null){
            offer.setKolicina(itemDTO.getKolicina());
            kolicina = itemDTO.getKolicina();
        }else{
            offer.setKolicina((long)1);
        }

        Float vpc = 0.0F;

        if(itemDTO.getCena() != null){
            offer.setVpc(itemDTO.getCena());
            vpc = itemDTO.getCena();
        }else{
            offer.setVpc(0.0F);
        }


        Float iznos = kolicina * vpc;
        offer.setIznos(iznos); //broj komada * cena

        Long rabat = 0L;

        if(itemDTO.getRabat() != null){
            offer.setRabat(itemDTO.getRabat());
            rabat = itemDTO.getRabat();
        }else{
            offer.setRabat(0L);
        }

        Float ukupno = (float) (iznos * (1.0 - rabat/100.0));
        offer.setUkupno(ukupno);
        Float pdv = 0.2F;
        Float iznosPdv = pdv*ukupno;
        offer.setIznosPdv(iznosPdv);
        offer.setFakturnaVrednost(ukupno + iznosPdv);
        offer.setZavrseno(false);

        Offer newOffer = this.offerService.addCustomOffer(offer, trenutnaPonuda);

        OfferDTO newOfferDTO = createDTOInstance(newOffer);

        return new ResponseEntity<>(newOfferDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateOffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDTO> updateOffer(@RequestBody OfferDTO offerDTO) throws Exception{
        System.out.println(offerDTO.getId());
        Optional<Offer> offer = this.offerService.findOne(offerDTO.getId());
        
        offer.get().setRabat(offerDTO.getRabat());
        offer.get().setKolicina(offerDTO.getKolicina());

        Float iznos = offerDTO.getVpc() * offerDTO.getKolicina();
        Long rabat = offerDTO.getRabat();

        offer.get().setIznos(iznos);

        Float ukupno = (float) (iznos * (1.0 - rabat/100.0));
        offer.get().setUkupno(ukupno);
        Float pdv = 0.2F;
        Float iznosPdv = pdv*ukupno;
        offer.get().setIznosPdv(iznosPdv);

        offer.get().setFakturnaVrednost(iznosPdv + ukupno);



        Offer updatedOffer = this.offerService.update(offer.get());
        OfferDTO updatedOfferTO =  createDTOInstance2(updatedOffer);

        return new ResponseEntity<>(updatedOfferTO, HttpStatus.OK);
    }

    @PutMapping(value = "/clearOffers",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Obracun> clearOffers(){

        Obracun obracun = this.offerService.clearOffers();

        return new ResponseEntity<>(obracun, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteOffer/{id}")
    public ResponseEntity deleteMachine(@PathVariable Long id) throws Exception{
        Optional<Offer> offer = this.offerService.findOne(id);
        this.offerService.delete(offer.get());

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<Void> deleteMachine() throws Exception{
        List<Offer> offers = this.offerService.findFreshOffers();
        for(Offer o : offers){
            this.offerService.delete(o);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



    public OfferDTO createDTOInstance(Offer offer){
        OfferDTO ret_val = new OfferDTO(
                offer.getId(),
                offer.getSifraArtikla(),
                offer.getOpisArtikla(),
                offer.getJedinicaMere(),
                offer.getKolicina(),
                offer.getVpc(),
                offer.getIznos(),
                offer.getRabat(),
                offer.getUkupno(),
                offer.getIznosPdv(),
                offer.getFakturnaVrednost()
        );
        ret_val.setZavrseno(false);
        return ret_val;
    }

    public OfferDTO createDTOInstance2(Offer offer){
        OfferDTO ret_val = new OfferDTO(
                offer.getId(),
                offer.getSifraArtikla(),
                offer.getOpisArtikla(),
                offer.getJedinicaMere(),
                offer.getKolicina(),
                offer.getVpc(),
                offer.getIznos(),
                offer.getRabat(),
                offer.getUkupno(),
                offer.getIznosPdv(),
                offer.getFakturnaVrednost(),
                offer.getZavrseno()
        );
        return ret_val;
    }

    public Offer createRegularInstance(OfferDTO offerDTO){
        Offer offer = new Offer(
                offerDTO.getId(),
                offerDTO.getSifraArtikla(),
                offerDTO.getOpisArtikla(),
                offerDTO.getJedinicaMere(),
                offerDTO.getKolicina(),
                offerDTO.getVpc(),
                offerDTO.getIznos(),
                offerDTO.getRabat(),
                offerDTO.getUkupno(),
                offerDTO.getIznosPdv(),
                offerDTO.getFakturnaVrednost()
        );
        offer.setZavrseno(false);
        return offer;
    }
    public Offer createRegularInstance2(OfferDTO offerDTO){
        Offer offer = new Offer(
                offerDTO.getId(),
                offerDTO.getSifraArtikla(),
                offerDTO.getOpisArtikla(),
                offerDTO.getJedinicaMere(),
                offerDTO.getKolicina(),
                offerDTO.getVpc(),
                offerDTO.getIznos(),
                offerDTO.getRabat(),
                offerDTO.getUkupno(),
                offerDTO.getIznosPdv(),
                offerDTO.getFakturnaVrednost()
        );
        offer.setZavrseno(offerDTO.getZavrseno());
        return offer;
    }
}
