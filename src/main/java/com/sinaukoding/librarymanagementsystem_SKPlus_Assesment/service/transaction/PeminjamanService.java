package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction;


import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;

public interface PeminjamanService {
    void pinjamBuku(PeminjamanRequestRecord request);
    void kembaliBuku(PeminjamanRequestRecord request);
}
