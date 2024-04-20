package com.example.AlatiMNS.service.impl;


import com.example.AlatiMNS.model.Offer;
import com.example.AlatiMNS.model.Ponuda;
import com.example.AlatiMNS.model.dto.Obracun;
import com.example.AlatiMNS.repository.OfferRepository;
import com.example.AlatiMNS.repository.PonudaRepository;
import com.example.AlatiMNS.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final PonudaRepository ponudaRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, PonudaRepository ponudaRepository){
        this.offerRepository = offerRepository;
        this.ponudaRepository = ponudaRepository;
    }

    @Override
    public List<Offer> findAll(){
        return this.offerRepository.findAll();
    }

    @Override
    public void delete(Offer offer) throws Exception{
        if(this.offerRepository.findById(offer.getId()) == null){
            throw new Exception("Offer you are trying to delete does not exist!");
        }
        offer.setPonuda(null);
        this.offerRepository.delete(offer);
    }

    @Override
    public Offer update(Offer offer) throws Exception{
        return this.offerRepository.save(offer);
    }

    @Override
    public Obracun clearOffers(){
        Optional<Ponuda> ponuda = this.ponudaRepository.findPonudaWithMaxTrenutnaVrednost();
        List<Offer> offers = this.offerRepository.findAll();
        List<Offer> freshOffers = new ArrayList<Offer>();

        for(Offer o : offers){
            if(o.getPonuda() != null){
                if(o.getPonuda().getTrenutnaPonuda() == ponuda.get().getTrenutnaPonuda() && o.getZavrseno() == false){
                    freshOffers.add(o);
                }
            }
        }
         Float ukupanIznosBezRabataIPdva = 0.0F;
         Float popust = 0.0F;
         Float osnovica = 0.0F;
         Float pdv = 0.0F;
         Float ukupno = 0.0F;
        for(Offer o : freshOffers){
            ukupanIznosBezRabataIPdva += o.getIznos();
            popust += (float) ((o.getRabat())/100.0)* o.getIznos();
            osnovica += o.getUkupno();
            pdv += o.getIznosPdv();
            ukupno += o.getFakturnaVrednost();

            o.setZavrseno(true);
            this.offerRepository.save(o);
        }
        Obracun obracun = new Obracun(ukupanIznosBezRabataIPdva, popust, osnovica, pdv, ukupno);

        Long trenutnaPonuda = ponuda.get().getTrenutnaPonuda();
        trenutnaPonuda += 1;

        Ponuda newPonuda = new Ponuda(trenutnaPonuda);
        this.ponudaRepository.save(newPonuda);
        return obracun;
    }

    @Override
    public Optional<Offer> findOne(Long id) throws Exception{
        Optional<Offer> offer = this.offerRepository.findById(id);
        if(offer == null){
            throw new Exception("Offer with this id does not exist!");
        }
        return offer;
    }

    @Override
    public Offer create(Offer offer) throws Exception{
        if(offer.getId() != null){
            throw new Exception("ID must be null!");
        }
        Optional<Ponuda> ponuda = this.ponudaRepository.findPonudaWithMaxTrenutnaVrednost();
        offer.setPonuda(ponuda.get());

        return this.offerRepository.save(offer);
    }

    @Override
    public Offer addCustomOffer(Offer offer, Long trenutnaPonuda) throws Exception{
        if(offer.getId() != null){
            throw new Exception("ID must be null!");
        }
        Optional<Ponuda> ponuda = this.ponudaRepository.findPonudaByTrenutnaPonuda(trenutnaPonuda);
        if(ponuda.isPresent()){
            offer.setPonuda(ponuda.get());
        }else{
            throw new Exception("Ponuda sa datim brojem ne postoji!");
        }

        return this.offerRepository.save(offer);
    }

    @Override
    public List<Offer> findOffersByTrenutnaPonuda(Long trenutnaPonuda){
        Optional<Ponuda> ponuda = this.ponudaRepository.findPonudaByTrenutnaPonuda(trenutnaPonuda);
        List<Offer> offers = this.offerRepository.findOffersByPonuda(ponuda.get());

        return offers;
    }

    @Override
    public List<Offer> findOffersByZavrsenoFalse(){
        List<Offer> offers = this.offerRepository.findOffersWithZavrsenoFalse();

        return offers;
    }

    @Override
    public List<Offer> findFreshOffers(){
        Optional<Ponuda> ponuda = this.ponudaRepository.findPonudaWithMaxTrenutnaVrednost();
        List<Offer> offers = this.offerRepository.findAll();
        List<Offer> ret_val = new ArrayList<Offer>();

        for(Offer o : offers){
            if(o.getPonuda() != null){
                if(o.getPonuda().getTrenutnaPonuda() == ponuda.get().getTrenutnaPonuda() && o.getZavrseno() == false){
                    ret_val.add(o);
                }
            }
        }
        return ret_val;
    }
}
