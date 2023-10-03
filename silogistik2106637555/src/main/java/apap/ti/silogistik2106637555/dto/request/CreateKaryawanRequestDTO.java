package apap.ti.silogistik2106637555.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    private String namaKaryawan;
    private int jenisKelaminKaryawan;
    private Date tanggalLahirKaryawan;
}
