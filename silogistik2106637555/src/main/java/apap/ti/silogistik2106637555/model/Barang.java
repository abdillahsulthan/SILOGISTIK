package apap.ti.silogistik2106637555.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    
    @Id
    private String sku;

    @NotNull
    @Column(name = "total_stok", nullable = false)
    private int totalStok;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private int tipeBarang;

    @NotNull
    @Column(name = "merk", nullable = false)
    private String merk;    

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private long hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GudangBarang> listGudangBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PermintaanPengirimanBarang> listPermintaanPengiriman;
}
