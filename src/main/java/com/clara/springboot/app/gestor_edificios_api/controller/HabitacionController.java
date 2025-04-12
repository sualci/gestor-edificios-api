package com.clara.springboot.app.gestor_edificios_api.controller;

import com.clara.springboot.app.gestor_edificios_api.dto.HabitacionDto;
import com.clara.springboot.app.gestor_edificios_api.model.Habitacion;
import com.clara.springboot.app.gestor_edificios_api.service.HabitacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/edificio/{edificioId}")
    public ResponseEntity<List<HabitacionDto>> getAllHabitacionesByEdificioId(@PathVariable Long edificioId) {
        return ResponseEntity.ok(habitacionService.getAllHabitacionesDtoByEdificioId(edificioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDto> getHabitacionById(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.getHabitacionDtoById(id));
    }

    @PostMapping("/edificio/{edificioId}")
    public ResponseEntity<Habitacion> createHabitacion(@PathVariable Long edificioId, @RequestBody Habitacion habitacion) {
        return new ResponseEntity<>(habitacionService.createHabitacion(edificioId, habitacion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitacionDto> updateHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacionDetails) {
        return ResponseEntity.ok(habitacionService.updateHabitacion(id, habitacionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        habitacionService.deleteHabitacion(id);
        return ResponseEntity.noContent().build();
    }
}