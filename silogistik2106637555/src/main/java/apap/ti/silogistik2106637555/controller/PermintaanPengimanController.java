package apap.ti.silogistik2106637555.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PermintaanPengimanController {
    
    @GetMapping("permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman() {
        return "form-create-permintaan-pengiriman";
    }
}