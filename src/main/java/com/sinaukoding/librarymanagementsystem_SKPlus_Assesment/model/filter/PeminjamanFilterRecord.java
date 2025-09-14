package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.IsReturned;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Role;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;

public record PeminjamanFilterRecord(String idUser,
                               String idBuku,
                               IsReturned isReturned) {
}
