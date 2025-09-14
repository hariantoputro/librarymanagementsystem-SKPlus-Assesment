package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.transaction;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.transaction.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, String>, JpaSpecificationExecutor<Peminjaman> {
    @Query(nativeQuery = true, value = "select * from t_peminjaman p where is_returned = 'BELUM' and id_user = :paramIdUser and id_buku = :paramIdBuku")
    Peminjaman findByParamIdUserAndParamIdBuku(String paramIdUser, String paramIdBuku);
}
