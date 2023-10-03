package apap.ti.silogistik2106637555.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106637555.dto.BarangMapper;
import apap.ti.silogistik2106637555.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106637555.service.BarangService;

@Controller
public class BarangController {
    
    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;

    @GetMapping("barang/tambah")
    public String formCreateBarang(Model model) {
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        return "form-create-barang";
    }

    @PostMapping("barang/tambah")
    public String createBarang(@ModelAttribute CreateBarangRequestDTO createBarangRequestDTO, Model model) {
        var barang = barangMapper.createBarangRequestDTOToBarang(createBarangRequestDTO);
        barangService.saveBarang(barang);
        model.addAttribute("sku", barang.getSku());
        return "success-create-barang";
    }
}