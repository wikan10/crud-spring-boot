package com.wikan.crud_spring_boot.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "proyek")
public class Proyek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_proyek")
    private String namaProyek;

    @Column(name = "tgl_mulai")
    private Timestamp tglMulai;

    @Column(name = "tgl_selesai")
    private Timestamp tglSelesai;

    @Column(name = "pimpinan_proyek")
    private String pimpinanProyek;

    private String keterangan;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "lokasi_id")
    private Lokasi lokasi;

    @OneToMany(mappedBy = "proyek", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProyekLokasi> proyekLokasiSet;
}