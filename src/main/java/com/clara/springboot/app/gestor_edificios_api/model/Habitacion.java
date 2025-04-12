package com.clara.springboot.app.gestor_edificios_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String tamano;

    private Integer capacidad;

    private Integer piso;

    private Double temperaturaActual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edificioId")
    private Edificio edificio;
}
