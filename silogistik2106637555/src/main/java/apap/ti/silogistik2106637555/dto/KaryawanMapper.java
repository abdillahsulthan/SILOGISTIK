package apap.ti.silogistik2106637555.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106637555.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106637555.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
  Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO); 
}
