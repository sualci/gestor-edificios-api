package com.clara.springboot.app.gestor_edificios_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EdificioDto {
    private Long id;
    private String nombre;
    private String direccion;
    private Integer numeroPisos;
    private Integer anoConstruccion;
    private List<HabitacionDto> habitaciones;
}
