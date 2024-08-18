package com.wikan.crud_spring_boot.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proyek_lokasi")
public class ProyekLokasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "proyek_id", nullable = false)
    private Proyek proyek;

    @ManyToOne
    @JoinColumn(name = "lokasi_id", nullable = false)
    private Lokasi lokasi;
}
