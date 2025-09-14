package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction.impl;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.builder.CustomBuilder;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.builder.CustomSpecification;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.builder.MultipleCriteria;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.builder.SearchCriteria;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.BukuImage;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.transaction.Peminjaman;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.transaction.PeminjamanMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.AppPage;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.SimpleMap;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.IsReturned;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.BukuFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.PeminjamanFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.managementuser.UserRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.master.BukuRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.transaction.PeminjamanRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app.ValidatorService;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction.PeminjamanService;
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
public class PeminjamanServiceImpl implements PeminjamanService {

    private final PeminjamanRepository peminjamanRepository;
    private final UserRepository userRepository;
    private final BukuRepository bukuRepository;
    private final ValidatorService validatorService;
    private final PeminjamanMapper peminjamanMapper;

    @Override
    public void pinjamBuku(PeminjamanRequestRecord request) {

            // validator mandatory
            validatorService.validator(request);
            var user = userRepository.findById(request.idUser())
                    .orElseThrow(() -> new RuntimeException("User dengan ID " + request.idUser() + " tidak ditemukan."));
            var buku = bukuRepository.findById(request.idBuku())
                    .orElseThrow(() -> new RuntimeException("Buku dengan ID " + request.idBuku() + " tidak ditemukan."));

        var peminjamanExisting = peminjamanRepository.findByParamIdUserAndParamIdBuku(request.idUser(), request.idBuku());
        if (peminjamanExisting == null) {
            if (request.jumlah() < 1) {
                log.warn("Jumlah buku minimal 1");
                throw new RuntimeException("Jumlah buku minimal 1.");
            }

            var peminjaman = peminjamanMapper.requestToEntity(request);
            peminjaman.setUser(user);
            peminjaman.setBuku(buku);
            peminjaman.setIsReturned(IsReturned.BELUM);
            peminjamanRepository.save(peminjaman);
        }else{
            throw new RuntimeException("User masih meminjam buku yang sama.");
        }
    }

    @Override
    public void kembaliBuku(PeminjamanRequestRecord request) {
        validatorService.validator(request);

        var peminjamanExisting = peminjamanRepository.findByParamIdUserAndParamIdBuku(request.idUser(), request.idBuku());
        if (peminjamanExisting != null) {
            var peminjaman = peminjamanMapper.requestToEntity(request);
            peminjaman.setId(peminjamanExisting.getId());
            peminjaman.setBuku(peminjamanExisting.getBuku());
            peminjaman.setUser(peminjamanExisting.getUser());
            peminjaman.setIsReturned(IsReturned.SUDAH);
            peminjamanRepository.save(peminjaman);
        }else{
            throw new RuntimeException("User dan Buku tidak ditemukan");
        }

    }

    @Override
    public Page<SimpleMap> findAll(PeminjamanFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Peminjaman> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotNullEqual("idUser", filterRequest.idUser(), builder);
        FilterUtil.builderConditionNotNullEqual("idBuku", filterRequest.idBuku(), builder);
        FilterUtil.builderConditionNotNullEqual("isReturned", filterRequest.isReturned(), builder);


        Page<Peminjaman> listPeminjaman = peminjamanRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listPeminjaman.stream().map(peminjaman -> {
            SimpleMap data = new SimpleMap();
            data.put("id", peminjaman.getId());
            data.put("user", peminjaman.getUser().getId());
            data.put("buku", peminjaman.getBuku().getId());
            data.put("status", peminjaman.getIsReturned().getLabel());
            data.put("createdDate", peminjaman.getCreatedDate());
            data.put("modifiedDate", peminjaman.getModifiedDate());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listPeminjaman.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var peminjaman = peminjamanRepository.findById(id).orElseThrow(() -> new RuntimeException("Data peminjaman tidak ditemukan"));

        SimpleMap data = new SimpleMap();
        data.put("id", peminjaman.getId());
        data.put("user", peminjaman.getUser().getId());
        data.put("buku", peminjaman.getBuku().getId());
        data.put("status", peminjaman.getIsReturned().getLabel());
        data.put("createdDate", peminjaman.getCreatedDate());
        data.put("modifiedDate", peminjaman.getModifiedDate());
        return data;
    }
}
