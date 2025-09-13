package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Role;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;

public record UserRequestRecord(String id,
                                String nama,
                                String username,
                                String email,
                                String password,
                                Status status,
                                Role role) {
}