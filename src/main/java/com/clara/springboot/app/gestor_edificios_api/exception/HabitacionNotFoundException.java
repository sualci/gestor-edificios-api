package com.clara.springboot.app.gestor_edificios_api.exception;

public class HabitacionNotFoundException extends RuntimeException {
    public HabitacionNotFoundException(Long id) {
        super("No se ha encontrado la habitacion con id: " + id);
    }

    public HabitacionNotFoundException(String code) {
        super("No se ha encontrado la habitacion con codigo: " + code);
    }
}