package apap.ti.silogistik2106637555.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGudang;

    @NotNull
    @Column(name = "nama_gudang", nullable = false)
    private String namaGudang;

    @NotNull
    @Column(name = "alamat_gudang", nullable = false)
    private String alamatGudang; 
    
    @ManyToMany(mappedBy = "listGudang", fetch = FetchType.LAZY)
    List<Barang> listBarang;
}
