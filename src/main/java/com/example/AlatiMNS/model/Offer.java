package com.example.AlatiMNS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Getter
@Setter
@Data
@Table(name = "Offer")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SIFRA_ARTIKLA")
    private String sifraArtikla;

    @Column(name = "OPIS_ARTIKLA")
    private String opisArtikla;

    @Column(name = "JEDINICA_MERE")
    private String jedinicaMere;

    @Column(name = "KOLICINA")
    private Long kolicina;

    @Column(name = "VPC")
    private Float vpc;

    @Column(name = "IZNOS")
    private Float iznos;

    @Column(name = "RABAT")
    private Long rabat;

    @Column(name = "UKUPNO")
    private Float ukupno;

    @Column(name = "IZNOS_PDV")
    private Float iznosPdv;

    @Column(name = "FAKTURNA_VREDNOST")
    private Float fakturnaVrednost;

    @Column(name = "ZAVRSENO")
    private Boolean zavrseno;

    @ManyToOne(cascade = CascadeType.ALL)
    //@ManyToOne
    @JoinColumn(name = "ponuda_id")
    private Ponuda ponuda;

    public Offer(){

    }

    public Offer(Long id, String sifraArtikla, String opisArtikla, String jedinicaMere, Long kolicina, Float vpc, Float iznos, Long rabat, Float ukupno, Float iznosPdv, Float fakturnaVrednost) {
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



    public Offer(String sifraArtikla, String opisArtikla, String jedinicaMere, Long kolicina, Float vpc, Float iznos, Long rabat, Float ukupno, Float iznosPdv, Float fakturnaVrednost) {
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
