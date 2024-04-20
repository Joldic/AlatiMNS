package com.example.AlatiMNS.service.impl;

import com.example.AlatiMNS.model.Offer;
import com.example.AlatiMNS.model.Ponuda;
import com.example.AlatiMNS.repository.OfferRepository;
import com.example.AlatiMNS.repository.PonudaRepository;
import com.example.AlatiMNS.service.PonudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PonudaServiceImpl implements PonudaService {
    private final PonudaRepository ponudaRepository;

    private final OfferRepository offerRepository;

    @Autowired
    public PonudaServiceImpl(PonudaRepository ponudaRepository, OfferRepository offerRepository){
        this.ponudaRepository = ponudaRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public Optional<Ponuda> findMaxPonuda() throws Exception{
        Optional<Ponuda> ponuda = this.ponudaRepository.findPonudaWithMaxTrenutnaVrednost();
        if(ponuda == null){
            throw new Exception("No ponuda found!");
        }
        return ponuda;
    }

    @Override
    public Ponuda create(Ponuda ponuda) throws Exception{
        if(ponuda.getId() != null){
            throw new Exception("ID must be null!");
        }
        return this.ponudaRepository.save(ponuda);
    }

    @Override
    public Ponuda findPonudaByTrenutnaPonuda(Long trenutnaPonuda) throws Exception{
        Optional<Ponuda> ponuda = this.ponudaRepository.findPonudaByTrenutnaPonuda(trenutnaPonuda);
        if(ponuda.isPresent()){
            return ponuda.get();
        }else{
            throw new Exception("Ponuda ne postoji!");
        }

    }

    @Override
    public void delete(Ponuda ponuda) throws Exception{
        if(this.ponudaRepository.findById(ponuda.getId()) == null){
            throw new Exception("Ponuda koju pokusavate da obrisete ne postoji!");
        }
        List<Offer> offers = this.offerRepository.findAll();
        for(Offer o : offers){
            if(o.getPonuda().getTrenutnaPonuda() == ponuda.getTrenutnaPonuda()){
                o.setPonuda(null);
                this.offerRepository.delete(o);
            }
        }

        this.ponudaRepository.delete(ponuda);
    }
}
