package com.clara.springboot.app.gestor_edificios_api.dto.mapper;


import com.clara.springboot.app.gestor_edificios_api.dto.HabitacionDto;
import com.clara.springboot.app.gestor_edificios_api.model.Habitacion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabitacionMapper {

    Habitacion toEntity(HabitacionDto habitacionDto);

    @Mappings({
            @Mapping(target = "edificioId", source = "edificio.id"),
            @Mapping(target = "edificioNombre", source = "edificio.nombre")})
    HabitacionDto toDto(Habitacion habitacion);

    // Map de listas
    List<Habitacion> toEntityList(List<HabitacionDto> habitacionDtoList);
    List<HabitacionDto> toDtoList(List<Habitacion> habitacionList);

}
