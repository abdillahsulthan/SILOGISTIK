package apap.ti.silogistik2106637555.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
@SQLDelete(sql = "UPDATE permintaan_pengiriman SET is_canceled = true WHERE id_permintaan_pengiriman=?")
@Where(clause = "is_canceled=false")
public class PermintaanPengiriman {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPermintaanPengiriman;

    @NotNull
    @Column(name = "nomor_pengiriman", unique = true, nullable = false)
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_canceled", nullable = false)
    private boolean isCanceled;

    @NotNull
    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @NotNull
    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private int biayaPengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private int jenisLayanan;

    @NotNull
    @Column(name = "waktu_permintaan", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPermintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan")
    private Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
