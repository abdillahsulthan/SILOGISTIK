package apap.ti.silogistik2106637555.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106637555.dto.BarangMapper;
import apap.ti.silogistik2106637555.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106637555.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106637555.model.Barang;
import apap.ti.silogistik2106637555.service.BarangService;
import apap.ti.silogistik2106637555.service.GudangService;
import apap.ti.silogistik2106637555.service.KaryawanService;
import apap.ti.silogistik2106637555.service.PermintaanPengirimanService;

@Controller
public class BarangController {
    
    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    GudangService gudangService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangMapper barangMapper;

    @GetMapping("/")
    public String beranda(Model model) {
        model.addAttribute("jumlahBarang", barangService.countBarangOnDB());
        model.addAttribute("jumlahPermintaanPengiriman", permintaanPengirimanService.countPermintaanPengirimanOnDB());
        model.addAttribute("jumlahGudang", gudangService.countGudangOnDB());
        model.addAttribute("jumlahKaryawan", karyawanService.countKaryawanOnDB());
        return "beranda";
    }

    @GetMapping("barang")
    public String listBarang(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);
        return "viewall-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        return "form-create-barang";
    }

    @PostMapping("barang/tambah")
    public String addBarang(@ModelAttribute CreateBarangRequestDTO barangDTO, Model model) {
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        barangService.saveBarang(barang);
        model.addAttribute("sku", barang.getSku());
        return "success-create-barang";
    }

    @GetMapping("barang/{idBarang}")
    public String detailBuku(@PathVariable("idBarang") String idBarang, Model model) {
        //Mendapatkan buku melalui kodeBuku
        var barang = barangService.getBarangBySku(idBarang);
        var readBarangDTO = barangMapper.barangToReadBarangResponseDTO(barang);
        model.addAttribute("barang", readBarangDTO);
        model.addAttribute("listGudangBarang", readBarangDTO.getListGudangBarang());
        return "view-barang";
    }     
    
    @GetMapping("barang/{idBarang}/ubah")
    public String formUpdateBarang(@PathVariable("idBarang") String idBarang, Model model) {
        var barang = barangService.getBarangBySku(idBarang);
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
        model.addAttribute("barangDTO", barangDTO);
        return "form-update-barang";
    }

    @PostMapping("barang/{idBarang}/ubah")
    public String updateBarang(@PathVariable("idBarang") String idBarang, @ModelAttribute UpdateBarangRequestDTO barangDTO, Model model) {
        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.updateBarang(barangFromDTO);
        model.addAttribute("sku", barang.getSku());
        return "success-update-barang";
    }

}