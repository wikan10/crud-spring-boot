package com.wikan.crud_spring_boot.controller;

import com.wikan.crud_spring_boot.model.ProyekLokasi;
import com.wikan.crud_spring_boot.service.ProyekLokasiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyek-lokasi")
@AllArgsConstructor
public class ProyekLokasiController {

    private final ProyekLokasiService proyekLokasiService;

    @GetMapping
    public ResponseEntity<List<ProyekLokasi>> findAllProyekLokasi() {
        List<ProyekLokasi> proyekLokasiList = proyekLokasiService.findAll();
        return new ResponseEntity<>(proyekLokasiList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProyekLokasi> saveNewProyekLokasi(@RequestBody ProyekLokasi proyekLokasi) {
        ProyekLokasi createdProyekLokasi = proyekLokasiService.createProyekLokasi(proyekLokasi);
        return new ResponseEntity<>(createdProyekLokasi, HttpStatus.CREATED);
    }
}
