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
@Table(name = "KUPAC")
public class Kupac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name="SIFRA")
    private String sifra;

    @Column(name="KUPAC")
    private String kupac;

    @Column(name = "MESTO")
    private String mesto;

    @Column(name = "ADRESA")
    private String adresa;

    @Column(name = "PIB")
    private String pib;

    @Column(name = "TEL")
    private String tel;

    public Kupac(){

    }

}
