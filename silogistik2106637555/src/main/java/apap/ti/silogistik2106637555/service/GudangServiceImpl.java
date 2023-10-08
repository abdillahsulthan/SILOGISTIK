package apap.ti.silogistik2106637555.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637555.model.Barang;
import apap.ti.silogistik2106637555.model.Gudang;
import apap.ti.silogistik2106637555.model.GudangBarang;
import apap.ti.silogistik2106637555.repository.BarangDb;
import apap.ti.silogistik2106637555.repository.GudangBarangDb;
import apap.ti.silogistik2106637555.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangDb gudangDb;

    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

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

    @Override
    public Gudang restockBarangToGudang(Gudang gudangFromDTO) {
        Gudang gudang = getGudangById(gudangFromDTO.getIdGudang());

        if (gudang != null) {
            List<GudangBarang> existingGudangBarangs = gudang.getListGudangBarang();

            Map<String, GudangBarang> existingGudangBarangMap = existingGudangBarangs.stream()
                    .collect(Collectors.toMap(gb -> gb.getBarang().getSku(), Function.identity()));

            for (GudangBarang gudangBarang : gudangFromDTO.getListGudangBarang()) {
                String sku = gudangBarang.getBarang().getSku();
                int newStok = gudangBarang.getStok();

                if (existingGudangBarangMap.containsKey(sku)) {
                    GudangBarang existingGudangBarang = existingGudangBarangMap.get(sku);
                    int updatedStok = existingGudangBarang.getStok() + newStok;
                    existingGudangBarang.setStok(updatedStok);
                } else {
                    gudangBarang.setGudang(gudang);
                    existingGudangBarangs.add(gudangBarang);
                }
            }

            gudang.setListGudangBarang(existingGudangBarangs);
            gudangDb.save(gudang);
        }

        List<GudangBarang> listGudangBarang = gudangBarangDb.findAll();

        Map<String, Integer> accumulatedStock = new HashMap<>();

        for (GudangBarang gudangBarang : listGudangBarang) {
            String currentSku = gudangBarang.getBarang().getSku();
            int currentStok = gudangBarang.getStok();

            if (accumulatedStock.containsKey(currentSku)) {
                int totalStok = accumulatedStock.get(currentSku) + currentStok;
                accumulatedStock.put(currentSku, totalStok);
            } else {
                accumulatedStock.put(currentSku, currentStok);
            }
        }

        for (Map.Entry<String, Integer> key : accumulatedStock.entrySet()) {
            Barang barang = barangDb.findBarangBySku(key.getKey());
            int totalStok = key.getValue();

            barang.setTotalStok(totalStok);
            barangDb.save(barang);
        }
        return gudang;
    }

    @Override
    public List<GudangBarang> filteredGudang(String sku) {
        List<GudangBarang> filteredGudangBarang = gudangBarangDb.findByBarang(barangDb.findBarangBySku(sku));
        if(filteredGudangBarang == null || filteredGudangBarang.size() == 0) {
            return null;
        }
        return filteredGudangBarang;
    }
}