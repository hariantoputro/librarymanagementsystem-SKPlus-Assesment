package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction;


import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.SimpleMap;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.BukuFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.PeminjamanFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PeminjamanService {
    void pinjamBuku(PeminjamanRequestRecord request);
    void kembaliBuku(PeminjamanRequestRecord request);
    Page<SimpleMap> findAll(PeminjamanFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}
