package com.example.AlatiMNS.service.impl;

import com.example.AlatiMNS.model.Kupac;
import com.example.AlatiMNS.repository.KupacRepository;
import com.example.AlatiMNS.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KupacServiceimpl implements KupacService {

    private final KupacRepository kupacRepository;

    @Autowired
    public KupacServiceimpl(KupacRepository kupacRepository){
        this.kupacRepository = kupacRepository;
    }
    @Override
    public Optional<Kupac> findKupacByKupac(String kupac) throws Exception{
        Optional<Kupac> getKupac = this.kupacRepository.findKupacByKupac(kupac);

        return getKupac;
    }

    @Override
    public Page<Kupac> findAll(Pageable pageable){
        return kupacRepository.findAll(pageable);
    }

    @Override
    public Page<Kupac> findBySearchTerm(String searchTerm, Pageable pageable){
        return kupacRepository.findByKupacContainingIgnoreCase(searchTerm, pageable);
    }

    @Override
    public Kupac create(Kupac kupac) throws Exception{
        return this.kupacRepository.save(kupac);
    }

    @Override
    public Optional<Kupac> findOne(Long id) throws Exception{
        Optional<Kupac> kupac = this.kupacRepository.findById(id);
        if(!kupac.isPresent()){
            throw new Exception("Kupac with this id does not exist!");
        }
        return kupac;
    }

    @Override
    public void delete(Kupac kupac) throws Exception{
        if(this.kupacRepository.findById(kupac.getId()) == null){
            throw new Exception("Kupac you are trying to delete does not exist!");
        }
        this.kupacRepository.delete(kupac);
    }

    @Override
    public Kupac update(Kupac kupac) throws Exception{
        return this.kupacRepository.save(kupac);
    }
}
