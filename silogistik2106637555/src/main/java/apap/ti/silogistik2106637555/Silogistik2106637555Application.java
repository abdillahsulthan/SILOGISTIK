package apap.ti.silogistik2106637555;

import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106637555.dto.BarangMapper;
import apap.ti.silogistik2106637555.dto.GudangMapper;
import apap.ti.silogistik2106637555.dto.KaryawanMapper;
import apap.ti.silogistik2106637555.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106637555.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106637555.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106637555.service.BarangService;
import apap.ti.silogistik2106637555.service.GudangService;
import apap.ti.silogistik2106637555.service.KaryawanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106637555Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106637555Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(KaryawanService karyawanService, GudangService gudangService, KaryawanMapper karyawanMapper, GudangMapper gudangMapper, BarangService barangService, BarangMapper barangMapper) {
		return args -> {
			var faker = new Faker(new Locale("id", "ID"));
			List<CreateKaryawanRequestDTO> listKaryawanDTO = new ArrayList<>(); 
			for (int i = 0; i < 10; i ++) {
				var karyawanDTO = new CreateKaryawanRequestDTO();
				var namaKaryawan = faker.name().fullName();
				var tanggalLahirKaryawan = faker.date().birthday();
				var jenisKelaminKaryawan = faker.number().numberBetween(1,3);

				karyawanDTO.setNamaKaryawan(namaKaryawan);
				karyawanDTO.setJenisKelaminKaryawan(jenisKelaminKaryawan);
				karyawanDTO.setTanggalLahirKaryawan(tanggalLahirKaryawan);

				listKaryawanDTO.add(karyawanDTO);
			}
			for (CreateKaryawanRequestDTO karyawanDTO : listKaryawanDTO) {
				var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
				karyawanService.saveKaryawan(karyawan);
			}

			List<CreateGudangRequestDTO> listGudangDTO = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				var gudangDTO = new CreateGudangRequestDTO();
				var kotaGudang = faker.address().city();
				var namaGudang = "Gudang " + kotaGudang;
				var alamatGudang = faker.address().fullAddress();

				gudangDTO.setNamaGudang(namaGudang);
				gudangDTO.setAlamatGudang(alamatGudang);

				listGudangDTO.add(gudangDTO);
			}
			for (CreateGudangRequestDTO gudangDTO : listGudangDTO) {
				var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
				gudangService.saveGudang(gudang);
			}

			List<CreateBarangRequestDTO> listBarangDTO = new ArrayList<>();
			for (int i = 0 ; i < 5; i++) {
				var fakeFood = faker.food();
				var barangDTO = new CreateBarangRequestDTO();
				var hargaBarang = faker.number();

				barangDTO.setTipeBarang(3);
				barangDTO.setMerk(fakeFood.dish());
				barangDTO.setHargaBarang(hargaBarang.numberBetween(10000, 100000));

				listBarangDTO.add(barangDTO);
			}
			for (CreateBarangRequestDTO barangDTO : listBarangDTO) {
				var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
				barangService.saveBarang(barang);
			}
		};
	}
}