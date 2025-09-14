package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Role;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.Status;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.UserRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.managementuser.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void addUserTest() {
        UserRequestRecord request = new UserRequestRecord(null,
                "Admin",
                "admin",
                "admin@email.com",
                "admin123",
                Status.AKTIF,
                Role.ADMIN
        );
        userService.add(request);
    }
}