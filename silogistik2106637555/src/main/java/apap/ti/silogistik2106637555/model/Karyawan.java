package apap.ti.silogistik2106637555.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "karyawan")
public class Karyawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idKaryawan;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String namaKaryawan;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelaminKaryawan;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahirKaryawan;

    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengiriman> listPermintaanPengiriman; 
}
