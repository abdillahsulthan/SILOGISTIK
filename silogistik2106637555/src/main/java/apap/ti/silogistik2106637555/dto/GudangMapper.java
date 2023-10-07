package apap.ti.silogistik2106637555.dto;

import java.util.ArrayList;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106637555.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106637555.dto.request.RestockGudangRequestDTO;
import apap.ti.silogistik2106637555.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106637555.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    RestockGudangRequestDTO gudangToRestockGudangRequestDTO(Gudang gudang);
    
    Gudang restockGudangRequestDTOToGudang(RestockGudangRequestDTO restockGudangRequestDTO);

    @Mapping(target = "listGudangBarang", ignore = true)
    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);
    @AfterMapping
    default void mappingListGudangBarang(Gudang gudang, @MappingTarget ReadGudangResponseDTO readGudangResponseDTO){
        if (gudang.getListGudangBarang() != null && gudang != null){
            readGudangResponseDTO.setListGudangBarang(gudang.getListGudangBarang());
        } else {
            readGudangResponseDTO.setListGudangBarang(new ArrayList<>());
        }
    }
}