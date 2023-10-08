package apap.ti.silogistik2106637555.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106637555.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106637555.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106637555.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    
    ReadPermintaanPengirimanResponseDTO permintaanPengirmanToReadPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);
    @AfterMapping
    default void setJenisLayanan(PermintaanPengiriman permintaanPengiriman, @MappingTarget ReadPermintaanPengirimanResponseDTO readPermintaanPengirimanResponseDTO) {
        String jenisLayananGenerated = "";
        int jenisLayanan = permintaanPengiriman.getJenisLayanan();
        if (jenisLayanan == 1) {
            jenisLayananGenerated = "SAM";
        }else if (jenisLayanan == 2) {
            jenisLayananGenerated = "KIL";
        }else if (jenisLayanan == 3) {
            jenisLayananGenerated = "REG";
        }else {
            jenisLayananGenerated = "HEM";
        }
        readPermintaanPengirimanResponseDTO.setJenisLayanan(jenisLayananGenerated);
    }
}
