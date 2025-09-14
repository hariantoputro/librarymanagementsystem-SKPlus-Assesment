package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.transaction;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.BukuFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.PeminjamanFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction.PeminjamanService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
            return BaseResponse.error("Gagal meminjam buku: " + e.getMessage(), null);
        }
    }

    @PostMapping("kembali-buku")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody PeminjamanRequestRecord request) {
        try {
            peminjamanService.kembaliBuku(request);
            return BaseResponse.ok("Data pengembalian disimpan", null);
        } catch (Exception e) {
            return BaseResponse.error("Gagal mengembalikan buku: " + e.getMessage(), null);
        }
    }

    @PostMapping("find-all")
    @Parameters({
            @Parameter(name = "page", description = "Page Number", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"), required = true),
            @Parameter(name = "size", description = "Size Per Page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"), required = true),
            @Parameter(name = "sort", description = "Sorting Data", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "modifiedDate,desc"), required = true)
    })
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody PeminjamanFilterRecord filterRequest) {
        return BaseResponse.ok(null, peminjamanService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, peminjamanService.findById(id));
    }
}
