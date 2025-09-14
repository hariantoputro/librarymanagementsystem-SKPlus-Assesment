package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.managementuser.User;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.IsReturned;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PeminjamanRequestRecord(String id,
                                @NotBlank(message = "User tidak boleh kosong") String idUser,
                                @NotBlank(message = "Buku tidak boleh kosong") String idBuku,
                                @NotNull(message = "Jumlah tidak boleh kosong") Integer jumlah,
                                @NotNull(message = "Status tidak boleh kosong") IsReturned isReturned) {
}
