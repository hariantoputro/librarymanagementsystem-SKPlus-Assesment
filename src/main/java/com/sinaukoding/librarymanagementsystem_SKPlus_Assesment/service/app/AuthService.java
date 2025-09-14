package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.managementuser.User;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.SimpleMap;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.LoginRequestRecord;

public interface AuthService {

    SimpleMap login(LoginRequestRecord request);

    void logout(User userLoggedIn);

}
