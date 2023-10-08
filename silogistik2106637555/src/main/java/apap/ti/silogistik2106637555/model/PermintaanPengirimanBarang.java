package apap.ti.silogistik2106637555.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permintaanpengiriman_barang")
public class PermintaanPengirimanBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "idPermintaanPengiriman", nullable = false)
    private PermintaanPengiriman permintaanPengiriman;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku", nullable = false)
    private Barang barang;

    @Column(name = "kuantitas_pengiriman", nullable = false)
    @Min(value = 1, message = "Kuantitas pengiriman harus lebih dari 0")
    private int kuantitasPengiriman;
}
