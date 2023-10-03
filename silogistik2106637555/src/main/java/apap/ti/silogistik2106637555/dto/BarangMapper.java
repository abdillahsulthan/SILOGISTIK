package apap.ti.silogistik2106637555.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106637555.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106637555.model.Barang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
}
