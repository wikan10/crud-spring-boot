package com.wikan.crud_spring_boot.service;

import com.wikan.crud_spring_boot.model.ProyekLokasi;
import com.wikan.crud_spring_boot.repository.ProyekLokasiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProyekLokasiServiceImpl implements ProyekLokasiService {
    private final ProyekLokasiRepository proyekLokasiRepository;

    @Override
    public List<ProyekLokasi> findAll() {
        return proyekLokasiRepository.findAll();
    }

    @Override
    public ProyekLokasi createProyekLokasi(ProyekLokasi proyekLokasi) {
        return proyekLokasiRepository.save(proyekLokasi);
    }
}
