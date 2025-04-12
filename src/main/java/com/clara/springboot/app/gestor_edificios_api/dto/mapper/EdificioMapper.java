package com.clara.springboot.app.gestor_edificios_api.dto.mapper;

import com.clara.springboot.app.gestor_edificios_api.dto.EdificioDto;
import com.clara.springboot.app.gestor_edificios_api.model.Edificio;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EdificioMapper {

    @Mapping(target = "habitaciones", source = "habitaciones")
    Edificio toEntity(EdificioDto edificioDto);

    @Mapping(target = "habitaciones", source = "habitaciones")
    EdificioDto toResponse(Edificio edificio);

    // Mapeo de listas
    List<Edificio> toEntityList(List<EdificioDto> edificioDtos);
    List<EdificioDto> toResponseList(List<Edificio> edificios);
}