package apap.ti.silogistik2106637555.service;

import apap.ti.silogistik2106637555.model.Barang;

import java.util.List;
public interface BarangService {
    long jumlahBarang();
    List<Barang> getAllBarang();
    List<Barang> availableBarang(List<Barang> listBarang);
    void saveBarang(Barang barang);
}
