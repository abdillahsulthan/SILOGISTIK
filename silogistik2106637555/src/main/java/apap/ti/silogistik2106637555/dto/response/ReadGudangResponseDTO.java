package apap.ti.silogistik2106637555.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import apap.ti.silogistik2106637555.model.Barang;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private long idGudang;
    private String namaGudang;
    private String alamatGudang;
    private List<Barang> listBarang;   
}