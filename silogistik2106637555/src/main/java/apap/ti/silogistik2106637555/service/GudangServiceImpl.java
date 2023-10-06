package apap.ti.silogistik2106637555.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637555.model.Barang;
import apap.ti.silogistik2106637555.model.Gudang;
import apap.ti.silogistik2106637555.model.GudangBarang;
import apap.ti.silogistik2106637555.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangDb gudangDb;

    @Override
    public void saveGudang(Gudang gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public long countGudangOnDB() {
        return gudangDb.count();
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(long idGudang) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getIdGudang() == idGudang) {
                return gudang;
            }
        }
        return null;
    }

    @Override
    public List<GudangBarang> getAvailableBarangFromGudang(Gudang gudang) {
        List<GudangBarang> listAvailableBarangFromGudang = new ArrayList<>();
        for (GudangBarang barangInGudang : gudang.getListGudangBarang()) {
            if(barangInGudang.getStok() > 0) {
                listAvailableBarangFromGudang.add(barangInGudang);
            }
        }
        return listAvailableBarangFromGudang;
    }
}