package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master.impl;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.master.BukuMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.master.BukuRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app.ValidatorService;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master.BukuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    }
}
