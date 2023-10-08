package apap.ti.silogistik2106637555.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637555.model.PermintaanPengiriman;
import apap.ti.silogistik2106637555.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106637555.repository.BarangDb;
import apap.ti.silogistik2106637555.repository.PermintaanPengirimanDb;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{
    
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    BarangDb barangDb;

    @Override
    public long countPermintaanPengirimanOnDB() {
        return permintaanPengirimanDb.count();
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAll();
    }

    @Override
    public PermintaanPengiriman addPermintaanPengiriman(PermintaanPengiriman permintaanPengirimanFromDTO) {
        var waktuPermintaan = new java.sql.Timestamp(System.currentTimeMillis());
        permintaanPengirimanFromDTO.setCanceled(false);
        permintaanPengirimanFromDTO.setWaktuPermintaan(waktuPermintaan);
        int kuantitasPengiriman = 0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanFromDTO.getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengirimanFromDTO);
            var barang = barangDb.findBarangBySku(permintaanPengirimanBarang.getBarang().getSku());
            permintaanPengirimanBarang.setBarang(barang);
            kuantitasPengiriman += permintaanPengirimanBarang.getKuantitasPengiriman();
        }
        
        generateNomorPengiriman(permintaanPengirimanFromDTO, kuantitasPengiriman);
        permintaanPengirimanDb.save(permintaanPengirimanFromDTO);
        return permintaanPengirimanFromDTO;
    }

    @Override
    public void generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman, int kuantitasPengiriman) {
        String jenisLayanan = "";
        String waktuPermintaan = permintaanPengiriman.getWaktuPermintaan().toString().substring(11, 19).replace("-", ":");
        if (permintaanPengiriman.getJenisLayanan() == 1) {
            jenisLayanan += "SAM";
        } else if (permintaanPengiriman.getJenisLayanan() == 2) {
            jenisLayanan += "KIL";
        } else if (permintaanPengiriman.getJenisLayanan() == 3) {
            jenisLayanan += "REG";
        } else {
            jenisLayanan += "HEM";
        }

        String nomorPengiriman = "REQ" + String.format("%02d", kuantitasPengiriman) + jenisLayanan + waktuPermintaan;
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(long idPermintaanPengiriman) {
        return permintaanPengirimanDb.findPermintaanPengirimanByIdPermintaanPengiriman(idPermintaanPengiriman);
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengirimanDb.delete(permintaanPengiriman);
    }    
}