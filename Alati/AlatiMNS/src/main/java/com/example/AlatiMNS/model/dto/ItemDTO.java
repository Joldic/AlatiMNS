package com.example.AlatiMNS.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private Long id;

    private String kodBroj;

    private String model;

    private String naziv;

    private String eanKod;

    private Float cena;

    private Long kolicina;

    private Long rabat;

    public ItemDTO(){

    }
    public ItemDTO(Long id, String kodBroj, String model, String naziv, String eanKod, Float cena) {
        this.id = id;
        this.kodBroj = kodBroj;
        this.model = model;
        this.naziv = naziv;
        this.eanKod = eanKod;
        this.cena = cena;
    }

    public ItemDTO(String kodBroj, String model, String naziv, String eanKod, Float cena) {
        this.kodBroj = kodBroj;
        this.model = model;
        this.naziv = naziv;
        this.eanKod = eanKod;
        this.cena = cena;
    }

    public ItemDTO(String kodBroj, String model, String naziv, String eanKod, Float cena, Long kolicina, Long rabat) {
        this.kodBroj = kodBroj;
        this.model = model;
        this.naziv = naziv;
        this.eanKod = eanKod;
        this.cena = cena;
        this.kolicina = kolicina;
        this.rabat = rabat;
    }
}
