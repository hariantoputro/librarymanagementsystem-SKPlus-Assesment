package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master.impl;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.builder.CustomBuilder;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.builder.CustomSpecification;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.BukuImage;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.master.BukuMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.AppPage;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.SimpleMap;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.BukuFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.master.BukuRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app.ValidatorService;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master.BukuService;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BukuServiceImpl implements BukuService {

    private final BukuRepository bukuRepository;
    private final ValidatorService validatorService;
    private final BukuMapper bukuMapper;

    @Override
    public void add(BukuRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data buku");
            log.debug("Request data buku: {}", request);

            // validator mandatory
            validatorService.validator(request);

            if (request.stok() < 0) {
                log.warn("Stok buku tidak boleh kurang dari 0");
            }

            var buku = bukuMapper.requestToEntity(request);
            bukuRepository.save(buku);

            log.info("Buku {} berhasil ditambahkan", request.nama());
            log.trace("Tambah data buku berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data buku gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(BukuRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var bukuExisting = bukuRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));
        var buku = bukuMapper.requestToEntity(request);
        buku.setId(bukuExisting.getId());
        bukuRepository.save(buku);
    }

    @Override
    public Page<SimpleMap> findAll(BukuFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Buku> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("nama", filterRequest.nama(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);
        FilterUtil.builderConditionNotNullEqual("stok", filterRequest.stok(), builder);

//        if (filterRequest.hargaBawah() != null && filterRequest.hargaAtas() != null) {
//            builder.with(MultipleCriteria.builder().criterias(
//                    SearchCriteria.OPERATOR_AND,
//                    SearchCriteria.of("harga", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.hargaBawah()),
//                    SearchCriteria.of("harga", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.hargaAtas())
//            ));
//        } else if (filterRequest.hargaAtas() != null) {
//            builder.with("harga", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.hargaAtas());
//        } else if (filterRequest.hargaBawah() != null) {
//            builder.with("harga", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.hargaBawah());
//        }
            builder.with("stok", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.stok());


        Page<Buku> listBuku = bukuRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listBuku.stream().map(buku -> {
            SimpleMap data = new SimpleMap();
            data.put("id", buku.getId());
            data.put("nama", buku.getNama());
            data.put("deskripsi", buku.getDeskripsi());
            data.put("stok", buku.getStok());
            data.put("status", buku.getStatus());
            data.put("createdDate", buku.getCreatedDate());
            data.put("modifiedDate", buku.getModifiedDate());
            data.put("listImage", buku.getListImage().stream().map(BukuImage::getPath).collect(Collectors.toSet()));
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listBuku.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var buku = bukuRepository.findById(id).orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        SimpleMap data = new SimpleMap();
        data.put("id", buku.getId());
        data.put("nama", buku.getNama());
        data.put("deskripsi", buku.getDeskripsi());
        data.put("stok", buku.getStok());
        data.put("status", buku.getStatus());
        data.put("createdDate", buku.getCreatedDate());
        data.put("modifiedDate", buku.getModifiedDate());
        data.put("listImage", buku.getListImage().stream().map(BukuImage::getPath).collect(Collectors.toSet()));

        return data;
    }
}
