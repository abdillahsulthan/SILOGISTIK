package apap.ti.silogistik2106637555.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637555.repository.PermintaanPengirimanDb;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Override
    public long jumlahPermintaanPengiriman() {
        return permintaanPengirimanDb.count();
    }
    
}