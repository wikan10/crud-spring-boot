package com.wikan.crud_spring_boot.service;

import com.wikan.crud_spring_boot.model.Proyek;
import com.wikan.crud_spring_boot.model.ProyekLokasi;

import java.util.List;
import java.util.Optional;

public interface ProyekService {
    Proyek createProyek(Proyek proyek);
    Proyek saveProyek(Proyek proyek);
    Proyek saveProyekWithLokasi(Proyek proyek);
    List<Proyek> getAllProyek();
    Optional<Proyek> getProyekById(Integer id);
    void deleteProyek(Integer id);
    Proyek updateProyek(Integer id, Proyek proyekDetails);
}
