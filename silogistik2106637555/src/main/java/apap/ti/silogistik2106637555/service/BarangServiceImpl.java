package apap.ti.silogistik2106637555.service;

import java.util.ArrayList;
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
    public long jumlahBarang() {
        return barangDb.count();
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public List<Barang> availableBarang(List<Barang> listBarang) {
        List<Barang> listAvailableBarang = new ArrayList<>();
        for (Barang barang : listBarang) {
            if(barang.getTotalStok() > 0) {
                listAvailableBarang.add(barang);
            }
        }
        return listAvailableBarang;
    }

    @Override
    public void saveBarang(Barang barang) {
        String sku = "";
        barang.setSku(sku);
        barangDb.save(barang);

        String idBarang = String.format("%03d", barang.getIdBarang());
        int tipeBarang = barang.getTipeBarang();
        if (tipeBarang == 1) {
            sku = "ELEC" + idBarang;
        }else if (tipeBarang == 2) {
            sku = "CLOT" + idBarang;
        }else if (tipeBarang == 3) {
            sku = "FOOD" + idBarang;
        }else if (tipeBarang == 4) {
            sku = "COSM" + idBarang;
        }else {
            sku = "TOOL" + idBarang;
        }
        
        barang.setSku(sku);
        barangDb.save(barang);
    }
}
