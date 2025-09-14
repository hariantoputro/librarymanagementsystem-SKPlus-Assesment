package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction.impl;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.master.BukuMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.transaction.PeminjamanMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.IsReturned;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.managementuser.UserRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.master.BukuRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.transaction.PeminjamanRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app.ValidatorService;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction.PeminjamanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

            if (request.jumlah() < 1) {
                log.warn("Jumlah buku minimal 1");
                throw new RuntimeException("Jumlah buku minimal 1.");
            }

            var peminjaman = peminjamanMapper.requestToEntity(request);
            peminjaman.setUser(user);
            peminjaman.setBuku(buku);
            peminjaman.setIsReturned(IsReturned.BELUM);
            peminjamanRepository.save(peminjaman);

    }

    @Override
    public void kembaliBuku(PeminjamanRequestRecord request) {

    }

//    @Override
//    public void kembaliBuku(BukuRequestRecord request) {
//        // validator mandatory
//        validatorService.validator(request);
//
//        var peminjamanExist = peminjamanRepository.findByIdUserAndIdBuku(request.id()).orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));
//        var buku = bukuMapper.requestToEntity(request);
//        buku.setId(bukuExisting.getId());
//        bukuRepository.save(buku);
//    }
}
