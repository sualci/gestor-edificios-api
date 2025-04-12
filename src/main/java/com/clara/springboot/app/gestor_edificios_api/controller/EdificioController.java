package com.clara.springboot.app.gestor_edificios_api.controller;

import com.clara.springboot.app.gestor_edificios_api.dto.EdificioDto;
import com.clara.springboot.app.gestor_edificios_api.dto.HabitacionDto;
import com.clara.springboot.app.gestor_edificios_api.model.Edificio;
import com.clara.springboot.app.gestor_edificios_api.model.Habitacion;
import com.clara.springboot.app.gestor_edificios_api.service.EdificioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edificios")
public class EdificioController {
    private final EdificioService edificioService;

    public EdificioController(EdificioService edificioService) {
        this.edificioService = edificioService;
    }

    @GetMapping
    public ResponseEntity<List<EdificioDto>> getAllEdificios() {
        return ResponseEntity.ok(edificioService.getAllEdificiosDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Edificio> getEdificioById(@PathVariable Long id) {
        return ResponseEntity.ok(edificioService.getEdificioById(id));
    }

    @PostMapping
    public ResponseEntity<Edificio> createEdificio(@RequestBody Edificio edificio) {
        return new ResponseEntity<>(edificioService.createEdificio(edificio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EdificioDto> updateEdificio(@PathVariable Long id, @RequestBody Edificio edificioDetails) {
        return ResponseEntity.ok(edificioService.updateEdificio(id, edificioDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdificio(@PathVariable Long id) {
        edificioService.deleteEdificio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{edificioId}/habitaciones")
    public ResponseEntity<List<HabitacionDto>> getAllHabitacionesByEdificioId(@PathVariable Long edificioId) {
        return ResponseEntity.ok(edificioService.getEdificioDtoById(edificioId).getHabitaciones());
    }
}
