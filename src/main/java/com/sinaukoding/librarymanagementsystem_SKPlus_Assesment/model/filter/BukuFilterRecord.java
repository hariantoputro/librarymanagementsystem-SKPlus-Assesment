package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;

public record BukuFilterRecord(
        String nama,
        Status status,
        Integer stok
) {
}
