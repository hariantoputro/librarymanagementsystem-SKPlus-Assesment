package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.master;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master.BukuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("buku")
@RequiredArgsConstructor
//@Tag(name = "Buku API")
public class BukuController {

    private final BukuService bukuService;

    @PostMapping("save")
    public BaseResponse<?> save(@RequestBody BukuRequestRecord request) {
        bukuService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }


}
