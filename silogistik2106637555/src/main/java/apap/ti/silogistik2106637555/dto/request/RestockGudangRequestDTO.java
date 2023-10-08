package apap.ti.silogistik2106637555.dto.request;

import apap.ti.silogistik2106637555.model.GudangBarang;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestockGudangRequestDTO extends CreateGudangRequestDTO{
    private long idGudang;

    @Valid
    private List<GudangBarang> listGudangBarang;
}