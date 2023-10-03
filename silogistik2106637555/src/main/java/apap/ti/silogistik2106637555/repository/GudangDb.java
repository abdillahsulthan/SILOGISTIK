package apap.ti.silogistik2106637555.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106637555.model.Gudang;

import java.util.List;

@Repository
public interface GudangDb extends JpaRepository<Gudang, Long>{
    List<Gudang> findAll();
}
