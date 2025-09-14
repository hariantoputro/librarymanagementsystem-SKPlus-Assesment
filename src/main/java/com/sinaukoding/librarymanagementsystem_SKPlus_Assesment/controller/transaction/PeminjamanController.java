package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.transaction;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction.PeminjamanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("peminjaman")
@RequiredArgsConstructor
@Tag(name = "Peminjaman API")
public class PeminjamanController {

    private final PeminjamanService peminjamanService;

    @PostMapping("pinjam-buku")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody PeminjamanRequestRecord request) {
        try {
            peminjamanService.pinjamBuku(request);
            return BaseResponse.ok("Data berhasil disimpan", null);
        } catch (Exception e) {
            // You can use a more specific exception like PeminjamanException if needed
            return BaseResponse.error("Gagal meminjam buku: " + e.getMessage(), null);
        }
//        peminjamanService.pinjamBuku(request);
//        return BaseResponse.ok("Data berhasil disimpan", null);
    }
}
