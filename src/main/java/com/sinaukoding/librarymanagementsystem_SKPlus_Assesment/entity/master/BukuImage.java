package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master;


import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.app.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_buku_image", indexes = {
        @Index(name = "idx_buku_image_created_date", columnList = "createdDate"),
        @Index(name = "idx_buku_image_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_buku_image_id_buku", columnList = "id_buku"),
        @Index(name = "idx_buku_image_path", columnList = "path")
})
public class BukuImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Column(nullable = false)
    private String path;

}
