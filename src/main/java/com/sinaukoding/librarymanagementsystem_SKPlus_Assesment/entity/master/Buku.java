package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.app.BaseEntity;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_buku", indexes = {
        @Index(name = "idx_buku_created_date", columnList = "createdDate"),
        @Index(name = "idx_buku_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_buku_status", columnList = "status"),
        @Index(name = "idx_buku_nama", columnList = "nama")
})
public class Buku extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(max = 100, message = "Max karakter 100")
    @Column(nullable = false)
    private String nama;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String deskripsi;

    @Min(value = 0, message = "Stok tidak boleh negatif")
    @Column(nullable = false)
    private Integer stok;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buku", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<BukuImage> listImage = new HashSet<>();

}
