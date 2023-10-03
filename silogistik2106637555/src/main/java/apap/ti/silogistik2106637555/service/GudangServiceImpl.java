package apap.ti.silogistik2106637555.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637555.model.Gudang;
import apap.ti.silogistik2106637555.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService{
    
    @Autowired
    GudangDb gudangDb;

    @Override
    public long jumlahGudang() {
        return gudangDb.count();
    }
    
    @Override
    public void saveGudang(Gudang gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(long id) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getIdGudang() == id) {
                return gudang;
            }
        }
        return null;
    }

    // @Override
    // public Gudang restockBarang(Gudang gudangFromDTO) {
    //     var gudang = getGudangById(gudangFromDTO.getIdGudang());
    //     if(gudang != null) {
    //         gudang.setListBarang(gudangFromDTO.getListBarang());
    //         gudangDb.save(gudang);
    //     }
    //     return gudang;
    // }
}
