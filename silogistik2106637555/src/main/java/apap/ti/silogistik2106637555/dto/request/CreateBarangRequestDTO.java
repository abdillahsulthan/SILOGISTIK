package apap.ti.silogistik2106637555.dto.request;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String merk;
    private int tipeBarang;
    private BigInteger hargaBarang;
}
