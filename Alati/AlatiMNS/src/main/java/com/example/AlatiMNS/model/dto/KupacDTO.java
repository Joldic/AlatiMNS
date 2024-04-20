package com.example.AlatiMNS.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KupacDTO {

    private Long id;

    private String sifra;

    private String kupac;

    private String mesto;

    private String adresa;

    private String pib;

    private String tel;


    public KupacDTO(){

    }

    public KupacDTO(Long id, String sifra, String kupac, String mesto, String adresa, String pib, String tel) {
        this.id = id;
        this.sifra = sifra;
        this.kupac = kupac;
        this.mesto = mesto;
        this.adresa = adresa;
        this.pib = pib;
        this.tel = tel;
    }

    public KupacDTO(String sifra, String kupac, String mesto, String adresa, String pib, String tel) {
        this.sifra = sifra;
        this.kupac = kupac;
        this.mesto = mesto;
        this.adresa = adresa;
        this.pib = pib;
        this.tel = tel;
    }
}
