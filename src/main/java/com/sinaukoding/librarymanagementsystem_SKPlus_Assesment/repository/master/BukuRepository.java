package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.master;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BukuRepository extends JpaRepository<Buku, String>, JpaSpecificationExecutor<Buku> {
}
