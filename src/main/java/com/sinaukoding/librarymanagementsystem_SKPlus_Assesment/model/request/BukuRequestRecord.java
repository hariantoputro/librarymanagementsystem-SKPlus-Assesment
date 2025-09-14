package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record BukuRequestRecord(String id,
                                  @NotBlank(message = "Judul tidak boleh kosong") String judul,
                                  @NotBlank(message = "Deskripsi tidak boleh kosong") String deskripsi,
                                  @NotBlank(message = "Deskripsi tidak boleh kosong") String penulis,
                                  @NotBlank(message = "Deskripsi tidak boleh kosong") String penerbit,
                                  @NotBlank(message = "Deskripsi tidak boleh kosong") String isbn,
                                  @NotNull(message = "Tahun tidak boleh kosong") Integer tahun,
                                  @NotNull(message = "Jumlah tidak boleh kosong") Integer jumlah,
                                  @NotNull(message = "Status tidak boleh kosong") Status status,
                                  @NotEmpty(message = "Gambar tidak boleh kosong") Set<String> listImage) {
}
