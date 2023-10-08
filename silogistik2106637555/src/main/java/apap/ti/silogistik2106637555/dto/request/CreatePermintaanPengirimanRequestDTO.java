package apap.ti.silogistik2106637555.dto.request;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import apap.ti.silogistik2106637555.model.Karyawan;
import apap.ti.silogistik2106637555.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private Karyawan karyawan;
    private String namaPenerima;
    private String alamatPenerima;
    @DateTimeFormat(iso = ISO.DATE)
    private Date tanggalPengiriman;
    private int jenisLayanan;
    private int biayaPengiriman;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
