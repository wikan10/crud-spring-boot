package com.wikan.crud_spring_boot.service;

import com.wikan.crud_spring_boot.model.Lokasi;
import com.wikan.crud_spring_boot.model.Proyek;
import com.wikan.crud_spring_boot.model.ProyekLokasi;
import com.wikan.crud_spring_boot.repository.LokasiRepository;
import com.wikan.crud_spring_boot.repository.ProyekLokasiRepository;
import com.wikan.crud_spring_boot.repository.ProyekRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyekServiceImpl implements ProyekService {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private ProyekLokasiRepository proyekLokasiRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    @Override
    public Proyek createProyek(Proyek proyek) {
        // Implement the logic to create a Proyek
        return saveProyek(proyek);
    }

    @Override
    public Proyek saveProyek(Proyek proyek) {
        return proyekRepository.save(proyek);
    }

    @Override
    @Transactional
    public Proyek saveProyekWithLokasi(Proyek proyek) {
        // Handle Lokasi entity
        Lokasi lokasi = proyek.getLokasi();

        if (lokasi != null) {
            // Check if Lokasi exists
            if (lokasi.getId() == null) {
                // Lokasi is new, save it to the database
                lokasi = lokasiRepository.save(lokasi);
            } else {
                // Lokasi exists, check if it's present in the database
                Optional<Lokasi> existingLokasi = lokasiRepository.findById(lokasi.getId());
                if (existingLokasi.isPresent()) {
                    lokasi = existingLokasi.get(); // Use the existing Lokasi
                } else {
                    // If not found, save it as new
                    lokasi = lokasiRepository.save(lokasi);
                }
            }
        }

        // Save Proyek entity
        proyek.setLokasi(lokasi); // Ensure Proyek is linked to the correct Lokasi
        Proyek savedProyek = proyekRepository.save(proyek);

        // Handle ProyekLokasi mapping
        if (lokasi != null) {
            ProyekLokasi proyekLokasi = new ProyekLokasi();
            proyekLokasi.setProyek(savedProyek);
            proyekLokasi.setLokasi(lokasi);
            proyekLokasiRepository.save(proyekLokasi);
        }

        return savedProyek;
    }



    @Override
    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    @Override
    public Optional<Proyek> getProyekById(Integer id) {
        return proyekRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteProyek(Integer id) {
        try {
            // Hapus entri ProyekLokasi yang terkait
            proyekLokasiRepository.deleteByProyekId(id);

            // Hapus Proyek
            proyekRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting Proyek: " + e.getMessage());
        }
    }

    @Override
    public Proyek updateProyek(Integer id, Proyek proyekDetails) {
        Proyek existingProyek = proyekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyek not found"));

        // Update fields
        existingProyek.setNamaProyek(proyekDetails.getNamaProyek());
        existingProyek.setTglMulai(proyekDetails.getTglMulai());
        existingProyek.setTglSelesai(proyekDetails.getTglSelesai());
        existingProyek.setPimpinanProyek(proyekDetails.getPimpinanProyek());
        existingProyek.setKeterangan(proyekDetails.getKeterangan());
        existingProyek.setCreatedAt(proyekDetails.getCreatedAt());

        // Update Lokasi if needed
        if (proyekDetails.getLokasi() != null) {
            Lokasi lokasi = proyekDetails.getLokasi();
            existingProyek.setLokasi(lokasi);
        }

        // Save updated Proyek
        return proyekRepository.save(existingProyek);
    }
}
