package apap.ti.silogistik2106637555.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @ManyToOne
    @JoinColumn(name = "id_gudang", referencedColumnName = "idGudang", nullable = false)
    private Gudang gudang;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku", nullable = false)
    private Barang barang;

    @Column(name = "stok", nullable = false)    
    @Min(value = 1, message = "Stok harus lebih dari 0")
    private int stok;
}
