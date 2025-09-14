package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums;

import lombok.Getter;

@Getter
public enum IsReturned {

    BELUM("Belum Kembali"),
    SUDAH("Sudah Kembali");

    private final String label;

    IsReturned(String label) {
        this.label = label;
    }

}
