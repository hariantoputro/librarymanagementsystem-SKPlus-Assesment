package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.master.Buku;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.master.BukuMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.master.BukuRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app.ValidatorService;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master.impl.BukuServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BukuServiceTest {

    @Mock
    private BukuRepository bukuRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private BukuMapper bukuMapper;

    @InjectMocks
    private BukuServiceImpl bukuService;

    @Test
    void testAddBuku_Success(){
        Set<String> listImage = new HashSet<>();
        listImage.add("path1");

        var request = new BukuRequestRecord(null,
                "Le Petite Prince",
                "Pangeran Kecil",
                "Penulis 1",
                "Penerbit 1",
                "1111-1111-1111-1111",
                2000,
                3,
                Status.AKTIF,
                listImage);

        var bukuEntity = new Buku();
        when(bukuMapper.requestToEntity(request)).thenReturn(bukuEntity);

        // when
        bukuService.add(request);

        // then
        verify(validatorService, times(1)).validator(request);
        verify(bukuRepository, times(1)).save(bukuEntity);
    }
}
