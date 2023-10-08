package apap.ti.silogistik2106637555.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    // @NotBlank(message = "Merk Barang tidak boleh kosong")
    private String merk;

    private int tipeBarang;

    // @Min(value = 0, message = "Harga Barang tidak boleh kurang dari 0")
    private long hargaBarang;
}
