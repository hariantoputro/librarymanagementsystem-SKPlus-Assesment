package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Role;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;

public record UserFilterRecord(String nama,
                               String email,
                               String username,
                               Status status,
                               Role role) {
}
