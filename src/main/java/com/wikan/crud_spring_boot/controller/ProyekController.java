package com.wikan.crud_spring_boot.controller;

import com.wikan.crud_spring_boot.model.Proyek;
import com.wikan.crud_spring_boot.service.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @PostMapping
    public ResponseEntity<Proyek> createProyek(@RequestBody Proyek proyek) {
        try {
            Proyek createdProyek = proyekService.saveProyekWithLokasi(proyek);
            return ResponseEntity.ok(createdProyek);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekService.getAllProyek();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable Integer id) {
        return proyekService.getProyekById(id)
                .map(proyek -> new ResponseEntity<>(proyek, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Integer id, @RequestBody Proyek proyekDetails) {
        Proyek proyek = proyekService.getProyekById(id)
                .orElseThrow(() -> new RuntimeException("Proyek not found"));
        proyek.setNamaProyek(proyekDetails.getNamaProyek());
        proyek.setTglMulai(proyekDetails.getTglMulai());
        proyek.setTglSelesai(proyekDetails.getTglSelesai());
        proyek.setPimpinanProyek(proyekDetails.getPimpinanProyek());
        proyek.setKeterangan(proyekDetails.getKeterangan());
        proyek.setCreatedAt(proyekDetails.getCreatedAt());

        Proyek updatedProyek = proyekService.saveProyek(proyek);
        return ResponseEntity.ok(updatedProyek);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyek(@PathVariable Integer id) {
        try {
            proyekService.deleteProyek(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
