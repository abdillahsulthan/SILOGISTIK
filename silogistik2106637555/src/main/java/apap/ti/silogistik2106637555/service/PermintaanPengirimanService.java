package apap.ti.silogistik2106637555.service;

import apap.ti.silogistik2106637555.model.PermintaanPengiriman;

import java.util.Date;
import java.util.List;

public interface PermintaanPengirimanService {
    long countPermintaanPengirimanOnDB();
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    PermintaanPengiriman addPermintaanPengiriman(PermintaanPengiriman permintaanPengirimanFromDTO);
    void generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman, int kuantitasPengiriman);
    PermintaanPengiriman getPermintaanPengirimanById(long idPermintaanPengiriman);
    void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengiriman> filterPermintaanPengiriman(Date startDate, Date endDate, String sku);
}
