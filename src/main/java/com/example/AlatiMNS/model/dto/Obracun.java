package com.example.AlatiMNS.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Obracun {
    private Float ukupanIznosBezRabataIPdva;
    private Float popust;
    private Float osnovica;
    private Float pdv;
    private Float ukupno;

    public Obracun(){

    }

    public Obracun(Float ukupanIznosBezRabataIPdva, Float popust, Float osnovica, Float pdv, Float ukupno) {
        this.ukupanIznosBezRabataIPdva = ukupanIznosBezRabataIPdva;
        this.popust = popust;
        this.osnovica = osnovica;
        this.pdv = pdv;
        this.ukupno = ukupno;
    }
}
