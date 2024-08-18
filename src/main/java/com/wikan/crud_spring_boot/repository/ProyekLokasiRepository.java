package com.wikan.crud_spring_boot.repository;

import com.wikan.crud_spring_boot.model.ProyekLokasi;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ProyekLokasi pl WHERE pl.proyek.id = :proyekId")
    void deleteByProyekId(Integer proyekId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProyekLokasi pl WHERE pl.lokasi.id = :lokasiId")
    void deleteByLokasiId(Integer lokasiId);
}
