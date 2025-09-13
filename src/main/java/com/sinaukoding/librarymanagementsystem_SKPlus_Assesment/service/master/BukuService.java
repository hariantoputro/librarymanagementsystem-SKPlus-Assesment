package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.master;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.SimpleMap;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.BukuRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BukuService {

    void add(BukuRequestRecord request);

    void edit(BukuRequestRecord request);

//    Page<SimpleMap> findAll(BukuFilterRecord filterRequest, Pageable pageable);
//
//    SimpleMap findById(String id);

}

