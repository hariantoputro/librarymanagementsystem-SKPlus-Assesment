package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.transaction;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.managementuser.User;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.transaction.Peminjaman;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PeminjamanMapper {

    public Peminjaman requestToEntity(PeminjamanRequestRecord request) {
        Peminjaman peminjaman = Peminjaman.builder()
                .jumlah(request.jumlah())
                .build();

        return peminjaman;
    }

}
