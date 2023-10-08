package apap.ti.silogistik2106637555.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106637555.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106637555.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106637555.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106637555.service.BarangService;
import apap.ti.silogistik2106637555.service.KaryawanService;
import apap.ti.silogistik2106637555.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        model.addAttribute("listPermintaanPengiriman", permintaanPengirimanService.getAllPermintaanPengiriman());
        return "viewall-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model) {
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("listKaryawanExisting", karyawanService.getAllKaryawan());
        return "form-create-permintaan-pengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model) {
        if (createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang() == null || createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().size() == 0) {
            createPermintaanPengirimanRequestDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }
        createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());
        model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);
        model.addAttribute("listKaryawanExisting", karyawanService.getAllKaryawan());
        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        return "form-create-permintaan-pengiriman";
    }

    @PostMapping("permintaan-pengiriman/tambah")
    public String addPermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> {
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());

            model.addAttribute("errors", errors);
            return "error-viewall";
        }
        if (createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang() == null || createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().size() == 0) {
            model.addAttribute("error", "Barang harus diisi");
            return "error-view";
        }
        var permintaanPengirimanFromDTO = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(createPermintaanPengirimanRequestDTO);
        var permintaanPengirimanBarang = permintaanPengirimanService.addPermintaanPengiriman(permintaanPengirimanFromDTO);
        model.addAttribute("nomorPengiriman", permintaanPengirimanBarang.getNomorPengiriman());
        return "success-create-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        var permintaanPengirimanDTO = permintaanPengirimanMapper.permintaanPengirmanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listPermintaanPengirimanBarang", permintaanPengirimanDTO.getListPermintaanPengirimanBarang());
        return "view-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String deletePermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        permintaanPengirimanService.cancelPermintaanPengiriman(permintaanPengiriman);
        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());
        return "success-cancel-permintaan-pengiriman";
    }
    
}