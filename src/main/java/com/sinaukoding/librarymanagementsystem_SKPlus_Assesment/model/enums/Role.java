package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    MEMBER("Member"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }

}
