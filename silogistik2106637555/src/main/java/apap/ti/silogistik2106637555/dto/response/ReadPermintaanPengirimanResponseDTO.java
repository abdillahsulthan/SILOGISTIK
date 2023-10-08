package apap.ti.silogistik2106637555.dto.response;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106637555.model.Karyawan;
import apap.ti.silogistik2106637555.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanResponseDTO{
    private String nomorPengiriman;
    private Date waktuPermintaan;
    private Karyawan karyawan;
    private Date tanggalPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    private String jenisLayanan;
    private int biayaPengiriman;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
