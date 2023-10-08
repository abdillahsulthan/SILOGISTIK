package apap.ti.silogistik2106637555.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106637555.model.PermintaanPengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long>{
    PermintaanPengiriman findPermintaanPengirimanByNomorPengiriman(String nomorPengiriman);
}
