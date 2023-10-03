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
public class BerandaController {

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
        model.addAttribute("jumlahBarang", barangService.jumlahBarang());
        model.addAttribute("jumlahPermintaanPengiriman", permintaanPengirimanService.jumlahPermintaanPengiriman());
        model.addAttribute("jumlahGudang", gudangService.jumlahGudang());
        model.addAttribute("jumlahKaryawan", karyawanService.jumlahKaryawan());
        return "beranda";
    }
    
}
