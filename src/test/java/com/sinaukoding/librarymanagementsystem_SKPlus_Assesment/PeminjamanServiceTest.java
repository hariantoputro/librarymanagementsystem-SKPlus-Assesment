package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.managementuser.User;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.transaction.Peminjaman;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.transaction.PeminjamanMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.IsReturned;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.PeminjamanRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.managementuser.UserRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.master.BukuRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.transaction.PeminjamanRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app.ValidatorService;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.transaction.impl.PeminjamanServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PeminjamanServiceTest {

    @Mock
    private BukuRepository bukuRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PeminjamanRepository peminjamanRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private PeminjamanMapper peminjamanMapper;

    @InjectMocks
    private PeminjamanServiceImpl peminjamanService;

    @Test
    void testPinjamBuku_Success(){

        var request = new PeminjamanRequestRecord(null,
                "a3e73047-a433-45a5-a6f5-9db13f803ae3",
                "2222222222",
                1,
                IsReturned.BELUM);

        var peminjamanEntity = new Peminjaman();
        when(peminjamanMapper.requestToEntity(request)).thenReturn(peminjamanEntity);

        // when
        peminjamanService.pinjamBuku(request);

        // then
        verify(validatorService, times(1)).validator(request);
        verify(peminjamanRepository, times(1)).save(peminjamanEntity);
    }

}