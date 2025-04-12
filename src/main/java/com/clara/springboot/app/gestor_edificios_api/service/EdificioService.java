package com.clara.springboot.app.gestor_edificios_api.service;

import com.clara.springboot.app.gestor_edificios_api.dto.EdificioDto;
import com.clara.springboot.app.gestor_edificios_api.dto.mapper.EdificioMapper;
import com.clara.springboot.app.gestor_edificios_api.exception.EdificioNotFoundException;
import com.clara.springboot.app.gestor_edificios_api.model.Edificio;
import com.clara.springboot.app.gestor_edificios_api.repository.EdificioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EdificioService {
    private final EdificioRepository edificioRepository;

    @Autowired
    private EdificioMapper edificioMapper;

    public EdificioService(EdificioRepository edificioRepository) {
        this.edificioRepository = edificioRepository;
    }

    public Edificio createEdificio(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public List<Edificio> getAllEdificios() {
        return edificioRepository.findAll();
    }

    public List<EdificioDto> getAllEdificiosDtos() {
        List<Edificio> edificios =  this. edificioRepository.findAll();
        return this.edificioMapper.toResponseList(edificios);
    }

    public Edificio getEdificioById(Long id) {
        return this.edificioRepository.findById(id)
                .orElseThrow(() -> new EdificioNotFoundException(id));

    }

    public EdificioDto getEdificioDtoById(Long id) {
        Edificio edificio = edificioRepository.findById(id)
                .orElseThrow(() -> new EdificioNotFoundException(id));
        return this.edificioMapper.toResponse(edificio);
    }

    public EdificioDto updateEdificio(Long id, Edificio edificioDetails) {
        Edificio edificio = getEdificioById(id);
        edificio.setNombre(edificioDetails.getNombre());
        edificio.setDireccion(edificioDetails.getDireccion());
        edificio.setNumeroPisos(edificioDetails.getNumeroPisos());
        edificio.setAnoConstruccion(edificioDetails.getAnoConstruccion());
        edificio = edificioRepository.save(edificio);
        return this.edificioMapper.toResponse(edificio);
    }

    public void deleteEdificio(Long id) {
        Edificio edificio = getEdificioById(id);
        edificioRepository.delete(edificio);
    }
}