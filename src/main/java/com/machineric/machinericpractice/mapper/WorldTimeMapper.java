package com.machineric.machinericpractice.mapper;

import com.machineric.machinericpractice.client.dto.WorldTimeResponse;
import com.machineric.machinericpractice.entity.WorldTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorldTimeMapper {
    WorldTimeMapper INSTANCE = Mappers.getMapper(WorldTimeMapper.class);

    @Mapping(target = "lastUpdateTime", source = "")
    @Mapping(target = "id", source = "")
    WorldTime worldTimeResponseToEntity(WorldTimeResponse response);

}
