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
@Table(name = "ITEM")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "KOD_BROJ")
    private String kodBroj;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "NAZIV")
    private String naziv;

    @Column(name = "EAN_KOD")
    private String eanKod;

    @Column(name = "CENA")
    private Float cena;
}
