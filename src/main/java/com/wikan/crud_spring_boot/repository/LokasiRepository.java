package com.wikan.crud_spring_boot.repository;

import com.wikan.crud_spring_boot.model.Lokasi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LokasiRepository extends JpaRepository<Lokasi, Integer> {
}
