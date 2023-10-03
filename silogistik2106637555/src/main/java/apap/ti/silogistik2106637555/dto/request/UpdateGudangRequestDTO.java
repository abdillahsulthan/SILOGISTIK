package apap.ti.silogistik2106637555.dto.request;

import apap.ti.silogistik2106637555.model.Barang;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO{
    @NotBlank
    private long idGudang;

    @NotBlank
    private List<Barang> listBarang;
}
