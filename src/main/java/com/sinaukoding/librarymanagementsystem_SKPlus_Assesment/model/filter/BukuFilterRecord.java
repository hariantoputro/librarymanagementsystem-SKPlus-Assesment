package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;

public record BukuFilterRecord(
        String judul,
        String penulis,
        String penerbit,
        String isbn,
        Status status,
        Integer jumlah,
        Integer tahunMin,
        Integer tahunMax
) {
}
