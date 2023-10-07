package apap.ti.silogistik2106637555.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String merk;
    private int tipeBarang;
    private long hargaBarang;
}
