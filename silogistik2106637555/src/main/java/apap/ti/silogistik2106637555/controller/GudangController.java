package apap.ti.silogistik2106637555.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106637555.dto.GudangMapper;
import apap.ti.silogistik2106637555.model.Barang;
import apap.ti.silogistik2106637555.service.BarangService;
import apap.ti.silogistik2106637555.service.GudangService;

@Controller
public class GudangController {
    
    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    GudangMapper gudangMapper;

    @GetMapping("gudang")
    public String listGudang(Model model) {
        model.addAttribute("listGudang", gudangService.getAllGudang());
        return "viewall-gudang";
    }

    @GetMapping("gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") long idGudang, Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        var gudangDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

        var listAvailableBarangFromGudang = gudangService.getAvailableBarangFromGudang(gudang);

        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("listAvailableBarangFromGudang", listAvailableBarangFromGudang);
        return "view-gudang";
    }

    // @GetMapping("gudang/{idGudang}/restock-barang")
    // public String restockGudang(@PathVariable("idGudang") long idGudang, Model model) {
    //     var gudang = gudangService.getGudangById(idGudang);
    //     var gudangDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

    //     model.addAttribute("gudangDTO", gudangDTO);
    //     model.addAttribute("listGudangBarang", gudangDTO.getListGudangBarang());
    //     model.addAttribute("listBarangExisting", barangService.getAllBarang());
    //     return "restock-gudang";
    // }
}