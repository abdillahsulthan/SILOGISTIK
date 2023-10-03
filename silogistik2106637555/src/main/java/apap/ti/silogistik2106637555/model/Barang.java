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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBarang; 
    
    @NotNull
    @Column(name = "sku", unique = true, nullable = false)
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
    private BigInteger hargaBarang;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "gudang_barang", joinColumns = @JoinColumn(name = "sku"), inverseJoinColumns = @JoinColumn(name = "id_gudang"))
    List<Gudang> listGudang;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permintaanpengiriman_barang", joinColumns = @JoinColumn(name = "sku"), inverseJoinColumns = @JoinColumn(name = "id_permintaan_barang"))
    List<PermintaanPengiriman> listPermintaanPengiriman;
}
