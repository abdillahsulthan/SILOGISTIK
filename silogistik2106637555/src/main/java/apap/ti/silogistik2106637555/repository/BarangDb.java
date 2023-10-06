package apap.ti.silogistik2106637555.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106637555.model.Barang;
import java.util.List;


@Repository
public interface BarangDb extends JpaRepository<Barang, Long>{
    List<Barang> findByTipeBarang(int tipeBarang);
}
