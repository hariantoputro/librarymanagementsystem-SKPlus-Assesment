package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.managementuser;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.managementuser.User;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.UserRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestToEntity(UserRequestRecord request) {
        return User.builder()
                .nama(request.nama().toUpperCase())
                .username(request.username().toLowerCase())
                .email(request.email().toLowerCase())
                .status(request.status())
                .role(request.role())
                .build();
    }

}
