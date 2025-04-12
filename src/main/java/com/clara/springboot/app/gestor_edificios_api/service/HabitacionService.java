package com.clara.springboot.app.gestor_edificios_api.service;

import com.clara.springboot.app.gestor_edificios_api.dto.HabitacionDto;
import com.clara.springboot.app.gestor_edificios_api.dto.mapper.HabitacionMapper;
import com.clara.springboot.app.gestor_edificios_api.exception.HabitacionNotFoundException;
import com.clara.springboot.app.gestor_edificios_api.model.Edificio;
import com.clara.springboot.app.gestor_edificios_api.model.Habitacion;
import com.clara.springboot.app.gestor_edificios_api.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final EdificioService edificioService;

    @Autowired
    private HabitacionMapper habitacionMapper;

    public HabitacionService(HabitacionRepository habitacionRepository, EdificioService edificioService) {
        this.habitacionRepository = habitacionRepository;
        this.edificioService = edificioService;
    }

    public Habitacion createHabitacion(Long edificioId, Habitacion habitacion) {
        Edificio edificio = edificioService.getEdificioById(edificioId);
        habitacion.setEdificio(edificio);
        return habitacionRepository.save(habitacion);
    }

    public List<Habitacion> getAllHabitacionesByEdificioId(Long edificioId) {
        return habitacionRepository.findAllHabitacionesByEdificioId(edificioId);
    }


    public List<HabitacionDto> getAllHabitacionesDtoByEdificioId(Long edificioId) {
        List<Habitacion> habitaciones = habitacionRepository.findAllHabitacionesByEdificioId(edificioId);
        return this.habitacionMapper.toDtoList(habitaciones);
    }

    public HabitacionDto getHabitacionDtoById(Long id) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new HabitacionNotFoundException(id));
        return habitacionMapper.toDto(habitacion);
    }

    public Habitacion getHabitacionById(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new HabitacionNotFoundException(id));
    }

    public HabitacionDto updateHabitacion(Long id, Habitacion habitacionDetails) {
        Habitacion habitacion = getHabitacionById(id);
        habitacion.setCode(habitacionDetails.getCode());
        habitacion.setTamano(habitacionDetails.getTamano());
        habitacion.setCapacidad(habitacionDetails.getCapacidad());
        habitacion.setPiso(habitacionDetails.getPiso());
        habitacion.setTemperaturaActual(habitacionDetails.getTemperaturaActual());
        habitacion = habitacionRepository.save(habitacion);
        return this.habitacionMapper.toDto(habitacion);
    }

    public void deleteHabitacion(Long id) {
        Habitacion habitacion = getHabitacionById(id);
        habitacionRepository.delete(habitacion);
    }

    public void updateTemperaturaActual(Long habitacionId, Double temperatura) {
        habitacionRepository.findById(habitacionId).ifPresent(habitacion -> {
            habitacion.setTemperaturaActual(temperatura);
            habitacionRepository.save(habitacion);

            System.out.printf("Temperatura actualizada: Habitación %d - %.2f°C%n",
                    habitacionId, temperatura);
        });
    }
}