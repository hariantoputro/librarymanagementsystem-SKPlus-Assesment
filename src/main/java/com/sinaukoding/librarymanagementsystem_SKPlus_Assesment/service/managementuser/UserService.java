package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.managementuser;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.SimpleMap;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.UserFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.UserRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void add(UserRequestRecord request);

    void edit(UserRequestRecord request);

    void hardDelete(String id);

    Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);
}
