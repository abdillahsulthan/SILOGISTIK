package apap.ti.silogistik2106637555.service;

import java.util.List;

import apap.ti.silogistik2106637555.model.Karyawan;

public interface KaryawanService {
    void saveKaryawan(Karyawan karyawan);
    long countKaryawanOnDB();
    List<Karyawan> getAllKaryawan();
}
