package apap.ti.silogistik2106637555.service;

import java.util.List;

import apap.ti.silogistik2106637555.model.Gudang;
import apap.ti.silogistik2106637555.model.GudangBarang;

public interface GudangService {
    void saveGudang(Gudang gudang);
    long countGudangOnDB();
    List<Gudang> getAllGudang();
    Gudang getGudangById(long idGudang);
    List<GudangBarang> getAvailableBarangFromGudang(Gudang gudang);
}
