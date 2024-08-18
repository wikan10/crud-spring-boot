package com.wikan.crud_spring_boot.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "lokasi")
public class Lokasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_lokasi")
    private String namaLokasi;

    @Column(name = "negara")
    private String negara;

    @Column(name = "provinsi")
    private String provinsi;

    @Column(name = "kota")
    private String kota;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "lokasi", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProyekLokasi> proyekLokasiSet;
}
