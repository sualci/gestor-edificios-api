package com.clara.springboot.app.gestor_edificios_api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.clara.springboot.app.gestor_edificios_api.model.Habitacion;
import com.clara.springboot.app.gestor_edificios_api.repository.HabitacionRepository;
import com.clara.springboot.app.gestor_edificios_api.service.HabitacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class HabitacionServiceTest {

    @Mock
    private HabitacionRepository habitacionRepository;

    @InjectMocks
    private HabitacionService habitacionService;


    @Test
    void actualizarTemperatura_correctamente() {
        // Preparacion
        Long habitacionId = 1L;
        Double nuevaTemperatura = 23.5;
        Habitacion habitacionExistente = new Habitacion();
        habitacionExistente.setId(habitacionId);

        when(habitacionRepository.findById(habitacionId)).thenReturn(Optional.of(habitacionExistente));
        when(habitacionRepository.save(any(Habitacion.class))).thenReturn(habitacionExistente);

        // Accion
        habitacionService.updateTemperaturaActual(habitacionId, nuevaTemperatura);

        // Assert
        assertEquals(nuevaTemperatura, habitacionExistente.getTemperaturaActual());
    }
}