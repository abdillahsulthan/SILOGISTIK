package apap.ti.silogistik2106637555.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    private String namaGudang;
    private String alamatGudang;
}
