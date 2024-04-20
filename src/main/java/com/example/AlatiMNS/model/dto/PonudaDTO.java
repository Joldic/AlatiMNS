package com.example.AlatiMNS.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PonudaDTO {
    private Long id;

    private Long trenutnaPonuda;

    public PonudaDTO(){

    }

    public PonudaDTO(Long id, Long trenutnaPonuda) {
        this.id = id;
        this.trenutnaPonuda = trenutnaPonuda;
    }

}
