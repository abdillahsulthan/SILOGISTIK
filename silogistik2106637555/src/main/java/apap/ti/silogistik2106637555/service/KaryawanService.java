package apap.ti.silogistik2106637555.service;

import apap.ti.silogistik2106637555.model.Karyawan;

public interface KaryawanService {
    void saveKaryawan(Karyawan karyawan);
    long countKaryawanOnDB();
}
