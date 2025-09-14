package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestRecord(@NotBlank String username,
                                 @NotBlank String password) {
}
