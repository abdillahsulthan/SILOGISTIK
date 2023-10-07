package apap.ti.silogistik2106637555.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gudang_barang")
public class GudangBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGudangBarang;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_gudang", referencedColumnName = "idGudang", nullable = false)
    private Gudang gudang;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku", nullable = false)
    private Barang barang;

    @NotNull
    @Column(name = "stok", nullable = false)
    private int stok;
}
