package apap.ti.silogistik2106637555.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106637555.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106637555.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106637555.model.Barang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO barangRequestDTO);

    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);
    @AfterMapping
    default void setTipeBarang(Barang barang, @MappingTarget ReadBarangResponseDTO readBarangResponseDTO) {
        String tipeBarangGenerated = "";
        int tipeBarang = barang.getTipeBarang();
        if (tipeBarang == 1) {
            tipeBarangGenerated = "Produk Elektronik";
        }else if (tipeBarang == 2) {
            tipeBarangGenerated = "Pakaian & Aksesoris";
        }else if (tipeBarang == 3) {
            tipeBarangGenerated = "Makanan & Minuman";
        }else if (tipeBarang == 4) {
            tipeBarangGenerated = "Kosmetik";
        }else {
            tipeBarangGenerated = "Perlengkapan Rumah";
        }
        readBarangResponseDTO.setTipeBarang(tipeBarangGenerated);
    }
}