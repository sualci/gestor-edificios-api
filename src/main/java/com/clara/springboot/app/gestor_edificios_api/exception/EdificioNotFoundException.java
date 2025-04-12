package com.clara.springboot.app.gestor_edificios_api.exception;

public class EdificioNotFoundException extends RuntimeException {
    public EdificioNotFoundException(Long id) {
        super("No se ha encontrado el edificio con id: " + id);
    }
}