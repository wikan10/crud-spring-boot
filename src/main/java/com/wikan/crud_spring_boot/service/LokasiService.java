package com.wikan.crud_spring_boot.service;

import com.wikan.crud_spring_boot.model.Lokasi;

import java.util.List;
import java.util.Optional;

public interface LokasiService {
    Lokasi saveLokasi(Lokasi lokasi);
    List<Lokasi> getAllLokasi();
    Optional<Lokasi> getLokasiById(Integer id);
    void deleteLokasi(Integer id);
    Lokasi updateLokasi(Integer id, Lokasi lokasi);
}
