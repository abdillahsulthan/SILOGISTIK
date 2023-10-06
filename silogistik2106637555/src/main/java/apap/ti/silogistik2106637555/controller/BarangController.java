package apap.ti.silogistik2106637555.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/")
    public String beranda(Model model) {
        model.addAttribute("jumlahBarang", barangService.countBarangOnDB());
        model.addAttribute("jumlahPermintaanPengiriman", permintaanPengirimanService.countPermintaanPengirimanOnDB());
        model.addAttribute("jumlahGudang", gudangService.countGudangOnDB());
        model.addAttribute("jumlahKaryawan", karyawanService.countKaryawanOnDB());
        return "beranda";
    }
}