package apap.ti.silogistik2106637555.service;

import java.util.List;

import apap.ti.silogistik2106637555.model.Barang;

public interface BarangService {
    long countBarangOnDB();
    void saveBarang(Barang barang);
    String generateSKU(Barang barang);
    List<Barang> getAllBarang();
    Barang getBarangBySku(String sku);
    Barang updateBarang(Barang barangFromDTO);
}
