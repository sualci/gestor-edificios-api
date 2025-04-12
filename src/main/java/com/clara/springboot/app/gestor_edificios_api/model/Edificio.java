package com.clara.springboot.app.gestor_edificios_api.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    private Integer numeroPisos;

    private Integer anoConstruccion;

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones = new ArrayList<>();
}
