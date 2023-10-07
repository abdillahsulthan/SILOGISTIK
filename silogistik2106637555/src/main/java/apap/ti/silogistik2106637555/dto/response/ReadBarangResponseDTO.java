package apap.ti.silogistik2106637555.dto.response;

import apap.ti.silogistik2106637555.model.GudangBarang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private String merk;
    private String tipeBarang;
    private int totalStok;
    private long hargaBarang;
    private List<GudangBarang> listGudangBarang;
}
