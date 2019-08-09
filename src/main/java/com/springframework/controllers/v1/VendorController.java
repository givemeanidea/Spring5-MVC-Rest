package com.springframework.controllers.v1;

import com.springframework.api.v1.model.VendorDTO;
import com.springframework.api.v1.model.VendorListDTO;
import com.springframework.services.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;
    public static final String BASE_URL = "/api/v1/vendors";

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<VendorListDTO> getAllVendors() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorListDTO> getVendorById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<VendorListDTO> createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return null;
    }

    @PutMapping({"/{id}"}) // todo are outer curly brackets required???
    public ResponseEntity<VendorListDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return null;
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<VendorListDTO> patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return null;
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        return null;
    }
}
