package com.wikan.crud_spring_boot.service;

import com.wikan.crud_spring_boot.model.ProyekLokasi;
import java.util.List;

public interface ProyekLokasiService {
    List<ProyekLokasi> findAll();
    ProyekLokasi createProyekLokasi(ProyekLokasi proyekLokasi);
}
