package apap.ti.silogistik2106637555.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106637555.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106637555.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106637555.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106637555.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);

    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);

    @Mapping(target = "namaGudang", ignore = true)
    @Mapping(target = "alamatGudang", ignore = true)
    @Mapping(target = "listBarang", ignore = true)
    @Mapping(target = "idGudang", ignore = true)
    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);

    @AfterMapping
    default void gudangToCreateGudangRequestDTO(Gudang gudang, @MappingTarget ReadGudangResponseDTO readGudangResponseDTO) {
        readGudangResponseDTO.setNamaGudang(gudang.getNamaGudang());
        readGudangResponseDTO.setAlamatGudang(gudang.getAlamatGudang());
        readGudangResponseDTO.setListBarang(gudang.getListBarang());
        readGudangResponseDTO.setIdGudang(gudang.getIdGudang());
    }
}
