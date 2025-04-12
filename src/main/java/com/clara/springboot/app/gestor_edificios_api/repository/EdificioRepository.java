package com.clara.springboot.app.gestor_edificios_api.repository;

import com.clara.springboot.app.gestor_edificios_api.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    Optional<Edificio> findById(Long edificioId);
}