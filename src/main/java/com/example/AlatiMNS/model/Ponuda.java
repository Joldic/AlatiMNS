package com.example.AlatiMNS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name = "Ponuda")
public class Ponuda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TRENUTNA_PONUDA")
    private Long trenutnaPonuda;

    @OneToMany(mappedBy = "ponuda")
    private List<Offer> offerList;

    public Ponuda(){

    }
    public Ponuda(Long id, Long trenutnaPonuda) {
        this.id = id;
        this.trenutnaPonuda = trenutnaPonuda;
    }

    public Ponuda(Long trenutnaPonuda) {
        this.trenutnaPonuda = trenutnaPonuda;
    }
}
