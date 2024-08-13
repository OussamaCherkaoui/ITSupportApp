package com.itSupport.ITsupportApp.mapper;

import com.itSupport.ITsupportApp.dto.SignalPanneDto;
import com.itSupport.ITsupportApp.model.SignalPanne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SignalPanneMapper {

    @Mapping(source = "user.id", target = "user_id")
    @Mapping(source = "equipement.id", target = "equipement_id")
    @Mapping(source = "panne.id", target = "panne_id")
    SignalPanneDto toDTO(SignalPanne signalPanne);


    @Mapping(source = "user_id", target = "user.id")
    @Mapping(source = "equipement_id", target = "equipement.id")
    @Mapping(source = "panne_id", target = "panne.id")
    SignalPanne toEntity(SignalPanneDto signalPanneDto);

    List<SignalPanneDto> toDTO(List<SignalPanne> signalPanneList);

    List<SignalPanne> toEntity(List<SignalPanneDto> signalPanneDtoList);
}
