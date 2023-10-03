package apap.ti.silogistik2106637555.service;

import java.util.List;

import apap.ti.silogistik2106637555.model.Gudang;

public interface GudangService {
    long jumlahGudang();
    void saveGudang(Gudang gudang);
    List<Gudang> getAllGudang();
    Gudang getGudangById(long id);
    // Gudang restockBarang(Gudang gudangFromDTO);
}
