package com.clara.springboot.app.gestor_edificios_api.repository;

import com.clara.springboot.app.gestor_edificios_api.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

        Optional<Habitacion> findById(Long habitacionId);

        List<Habitacion> findAllHabitacionesByEdificioId(Long edificioId);
}
