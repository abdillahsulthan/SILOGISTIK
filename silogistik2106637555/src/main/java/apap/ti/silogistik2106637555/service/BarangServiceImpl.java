package apap.ti.silogistik2106637555.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637555.model.Barang;
import apap.ti.silogistik2106637555.repository.BarangDb;

@Service
public class BarangServiceImpl implements BarangService{

    @Autowired
    BarangDb barangDb;

    @Override
    public long countBarangOnDB() {
        return barangDb.count();
    }

    @Override
    public void saveBarang(Barang barang) {
        barang.setSku(generateSKU(barang));
        barangDb.save(barang);
    }

    @Override
    public String generateSKU(Barang barang) {
        String sku = "";
        int tipeBarang = barang.getTipeBarang();
        String barangKeBerapa = String.format("%03d", (barangDb.findByTipeBarang(tipeBarang).size() + 1));
        if (tipeBarang == 1) {
            sku = "ELEC" + barangKeBerapa;
        }else if (tipeBarang == 2) {
            sku = "CLOT" + barangKeBerapa;
        }else if (tipeBarang == 3) {
            sku = "FOOD" + barangKeBerapa;
        }else if (tipeBarang == 4) {
            sku = "COSM" + barangKeBerapa;
        }else {
            sku = "TOOL" + barangKeBerapa;
        }
        return sku;
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public Barang getBarangBySku(String sku) {
        return barangDb.findBarangBySku(sku);
    }

    @Override
    public Barang updateBarang(Barang barangFromDTO) {
        Barang barang = getBarangBySku(barangFromDTO.getSku());
        if(barang != null) {
            barang.setMerk(barangFromDTO.getMerk());
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }
}
