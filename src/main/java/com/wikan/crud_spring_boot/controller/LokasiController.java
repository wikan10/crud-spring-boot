package com.wikan.crud_spring_boot.controller;

import com.wikan.crud_spring_boot.model.Lokasi;
import com.wikan.crud_spring_boot.service.LokasiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {

    @Autowired
    private LokasiService lokasiService;

    @PostMapping
    public ResponseEntity<Lokasi> createLokasi(@RequestBody Lokasi lokasi) {
        Lokasi savedLokasi = lokasiService.saveLokasi(lokasi);
        return new ResponseEntity<>(savedLokasi, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Lokasi>> getAllLokasi() {
        List<Lokasi> lokasiList = lokasiService.getAllLokasi();
        return new ResponseEntity<>(lokasiList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lokasi> getLokasiById(@PathVariable Integer id) {
        Optional<Lokasi> lokasi = lokasiService.getLokasiById(id);
        return lokasi.isPresent() ? new ResponseEntity<>(lokasi.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lokasi> updateLokasi(@PathVariable Integer id, @RequestBody Lokasi lokasi) {
        try {
            Lokasi updatedLokasi = lokasiService.updateLokasi(id, lokasi);
            return new ResponseEntity<>(updatedLokasi, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLokasi(@PathVariable Integer id) {
        lokasiService.deleteLokasi(id);
        return ResponseEntity.noContent().build();
    }
}
