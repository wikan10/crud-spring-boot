package com.wikan.crud_spring_boot.repository;

import com.wikan.crud_spring_boot.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyekRepository extends JpaRepository<Proyek, Integer> {
}
