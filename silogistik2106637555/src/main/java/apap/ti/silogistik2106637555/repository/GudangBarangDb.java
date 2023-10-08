package apap.ti.silogistik2106637555.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106637555.model.Barang;
import apap.ti.silogistik2106637555.model.GudangBarang;
import java.util.List;


@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long>{
    List<GudangBarang> findByBarang(Barang barang);
}
