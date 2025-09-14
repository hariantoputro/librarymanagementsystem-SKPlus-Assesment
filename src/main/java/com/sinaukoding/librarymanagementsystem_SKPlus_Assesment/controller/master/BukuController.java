package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.master;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.BukuFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master.BukuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("buku")
@RequiredArgsConstructor
//@Tag(name = "Buku API")
public class BukuController {

    private final BukuService bukuService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody BukuRequestRecord request) {
        bukuService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody BukuRequestRecord request) {
        bukuService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
//    @Parameters({
//            @Parameter(name = "page", description = "Page Number", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"), required = true),
//            @Parameter(name = "size", description = "Size Per Page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"), required = true),
//            @Parameter(name = "sort", description = "Sorting Data", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "modifiedDate,desc"), required = true)
//    })
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody BukuFilterRecord filterRequest) {
        return BaseResponse.ok(null, bukuService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, bukuService.findById(id));
    }

}
