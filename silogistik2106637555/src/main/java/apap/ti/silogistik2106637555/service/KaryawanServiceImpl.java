package apap.ti.silogistik2106637555.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637555.model.Karyawan;
import apap.ti.silogistik2106637555.repository.KaryawanDb;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public void saveKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
    }

    @Override
    public long countKaryawanOnDB() {
        return karyawanDb.count();
    }
    
}
