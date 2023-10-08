package apap.ti.silogistik2106637555.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106637555.dto.GudangMapper;
import apap.ti.silogistik2106637555.dto.request.RestockGudangRequestDTO;
import apap.ti.silogistik2106637555.model.Gudang;
import apap.ti.silogistik2106637555.model.GudangBarang;
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

    @GetMapping("gudang/{idGudang}/restock-barang")
    public String restockGudang(@PathVariable("idGudang") long idGudang, Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        var restockGudangDTO = gudangMapper.gudangToRestockGudangRequestDTO(gudang);
        model.addAttribute("restockGudangDTO", restockGudangDTO);
        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        return "restock-gudang";
    }

    @PostMapping(value = "gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowGudangBarangUpdate(@PathVariable("idGudang") long idGudang, @ModelAttribute RestockGudangRequestDTO restockGudangDTO, Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        restockGudangDTO.setNamaGudang(gudang.getNamaGudang());
        restockGudangDTO.setAlamatGudang(gudang.getAlamatGudang());

        if (restockGudangDTO.getListGudangBarang() == null || restockGudangDTO.getListGudangBarang().size() == 0) {
            restockGudangDTO.setListGudangBarang(new ArrayList<>());
        }
        restockGudangDTO.getListGudangBarang().add(new GudangBarang());
        model.addAttribute("restockGudangDTO", restockGudangDTO);
        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        return "restock-gudang";
    }

    @PostMapping("gudang/{idGudang}/restock-barang")
    public String restockGudangSuccess(@PathVariable("idGudang") long idGudang, @ModelAttribute RestockGudangRequestDTO restockGudangDTO, Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        restockGudangDTO.setNamaGudang(gudang.getNamaGudang());
        restockGudangDTO.setAlamatGudang(gudang.getAlamatGudang());

        var gudangFromDTO = gudangMapper.restockGudangRequestDTOToGudang(restockGudangDTO);
        
        for (GudangBarang gudangBarang : gudangFromDTO.getListGudangBarang()) {
            String sku = gudangBarang.getBarang().getSku();
            gudangBarang.setGudang(gudangService.getGudangById(idGudang));
            gudangBarang.setBarang(barangService.getBarangBySku(sku));
        }

        var gudangFix = gudangService.restockBarangToGudang(gudangFromDTO);
        model.addAttribute("namaGudang", gudangFix.getNamaGudang());
        return "success-restock-gudang";
    }

    @GetMapping("gudang/cari-barang")
    public String searchBarangFromGudang(@RequestParam(value = "sku", required = false) String sku, Model model) {
        List<GudangBarang> listGudangBarangFiltered = gudangService.filteredGudang(sku);
        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("listGudangBarangFiltered", listGudangBarangFiltered);
        return "cari-barang";
    }
}