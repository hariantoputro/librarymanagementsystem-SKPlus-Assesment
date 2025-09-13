package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.master;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.BukuImage;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BukuMapper {

    public Buku requestToEntity(BukuRequestRecord request) {
        Buku buku = Buku.builder()
                .nama(request.nama().toUpperCase())
                .deskripsi(request.deskripsi())
                .stok(request.stok())
                .status(request.status())
                .build();

        buku.setListImage(request.listImage().stream()
                .map(path -> BukuImage.builder()
                        .path(path)
                        .buku(buku)
                        .build())
                .collect(Collectors.toSet()));

        return buku;
    }

}
