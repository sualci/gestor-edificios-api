package com.clara.springboot.app.gestor_edificios_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HabitacionDto {
    private Long id;
    private String code;
    private String tamano;
    private Integer capacidad;
    private Integer piso;
    private Double temperaturaActual;
    private Long edificioId;
    private String edificioNombre;
}
