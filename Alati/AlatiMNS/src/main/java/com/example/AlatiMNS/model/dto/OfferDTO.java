package com.example.AlatiMNS.model.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDTO {

    private Long id;

    private String sifraArtikla;

    private String opisArtikla;

    private String jedinicaMere;

    private Long kolicina;

    private Float vpc;

    private Float iznos;

    private Long rabat;

    private Float ukupno;

    private Float iznosPdv;

    private Float fakturnaVrednost;

    private Boolean zavrseno;

    public OfferDTO(){

    }

    public OfferDTO(Long id, String sifraArtikla, String opisArtikla, String jedinicaMere, Long kolicina, Float vpc, Float iznos, Long rabat, Float ukupno, Float iznosPdv, Float fakturnaVrednost) {
        this.id = id;
        this.sifraArtikla = sifraArtikla;
        this.opisArtikla = opisArtikla;
        this.jedinicaMere = jedinicaMere;
        this.kolicina = kolicina;
        this.vpc = vpc;
        this.iznos = iznos;
        this.rabat = rabat;
        this.ukupno = ukupno;
        this.iznosPdv = iznosPdv;
        this.fakturnaVrednost = fakturnaVrednost;
        this.zavrseno = false;
    }

    public OfferDTO(Long id, String sifraArtikla, String opisArtikla, String jedinicaMere, Long kolicina, Float vpc, Float iznos, Long rabat, Float ukupno, Float iznosPdv, Float fakturnaVrednost, Boolean zavrseno) {
        this.id = id;
        this.sifraArtikla = sifraArtikla;
        this.opisArtikla = opisArtikla;
        this.jedinicaMere = jedinicaMere;
        this.kolicina = kolicina;
        this.vpc = vpc;
        this.iznos = iznos;
        this.rabat = rabat;
        this.ukupno = ukupno;
        this.iznosPdv = iznosPdv;
        this.fakturnaVrednost = fakturnaVrednost;
        this.zavrseno = zavrseno;
    }

    public OfferDTO(String sifraArtikla, String opisArtikla, String jedinicaMere, Long kolicina, Float vpc, Float iznos, Long rabat, Float ukupno, Float iznosPdv, Float fakturnaVrednost) {
        this.sifraArtikla = sifraArtikla;
        this.opisArtikla = opisArtikla;
        this.jedinicaMere = jedinicaMere;
        this.kolicina = kolicina;
        this.vpc = vpc;
        this.iznos = iznos;
        this.rabat = rabat;
        this.ukupno = ukupno;
        this.iznosPdv = iznosPdv;
        this.fakturnaVrednost = fakturnaVrednost;
        this.zavrseno = false;
    }


}
