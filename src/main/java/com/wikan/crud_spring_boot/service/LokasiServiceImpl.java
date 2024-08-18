package com.wikan.crud_spring_boot.service;

import com.wikan.crud_spring_boot.model.Lokasi;
import com.wikan.crud_spring_boot.repository.LokasiRepository;
import com.wikan.crud_spring_boot.repository.ProyekLokasiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LokasiServiceImpl implements LokasiService {

    @Autowired
    private LokasiRepository lokasiRepository;

    @Autowired
    private ProyekLokasiRepository proyekLokasiRepository;

    @Override
    public Lokasi saveLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    @Override
    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    @Override
    public Optional<Lokasi> getLokasiById(Integer id) {
        return lokasiRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteLokasi(Integer id) {
        try {
            // Hapus entri ProyekLokasi yang terkait dengan Lokasi
            proyekLokasiRepository.deleteByLokasiId(id);

            // Hapus Lokasi
            lokasiRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting Lokasi: " + e.getMessage());
        }
    }

    @Override
    public Lokasi updateLokasi(Integer id, Lokasi lokasiDetails) {
        Lokasi existingLokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lokasi not found"));

        // Update fields
        existingLokasi.setNamaLokasi(lokasiDetails.getNamaLokasi());
        existingLokasi.setNegara(lokasiDetails.getNegara());
        existingLokasi.setProvinsi(lokasiDetails.getProvinsi());
        existingLokasi.setKota(lokasiDetails.getKota());
        existingLokasi.setCreatedAt(lokasiDetails.getCreatedAt());

        // Save updated lokasi
        return lokasiRepository.save(existingLokasi);
    }
}
