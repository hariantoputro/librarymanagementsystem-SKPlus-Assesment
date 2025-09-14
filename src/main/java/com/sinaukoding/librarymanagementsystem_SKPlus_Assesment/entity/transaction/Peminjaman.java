package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.transaction;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.app.BaseEntity;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.managementuser.User;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.IsReturned;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_peminjaman", indexes = {
        @Index(name = "idx_peminjaman_created_date", columnList = "createdDate"),
        @Index(name = "idx_peminjaman_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_peminjaman_id_user", columnList = "id_user"),
        @Index(name = "idx_peminjaman_id_buku", columnList = "id_buku"),
        @Index(name = "idx_peminjaman_returned_date", columnList = "returnedDate"),
        @Index(name = "idx_peminjaman_is_returned", columnList = "isReturned")
})

public class Peminjaman extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Min(value = 0, message = "Jumlah tidak boleh negatif")
    @Column(nullable = false)
    private Integer jumlah;

    @Column()
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsReturned isReturned;

}
