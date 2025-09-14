package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.app;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.config.UserLoggedInConfig;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.LoginRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Auth API")

public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public BaseResponse<?> login(@RequestBody LoginRequestRecord request) {
        return BaseResponse.ok("Berhasil login sebagai "+request.username(), authService.login(request));
    }

    @GetMapping("logout")
    public BaseResponse<?> logout(@AuthenticationPrincipal UserLoggedInConfig userLoggedInConfig) {
        var userLoggedIn = userLoggedInConfig.getUser();
        authService.logout(userLoggedIn);
        return BaseResponse.ok(userLoggedIn.getUsername()+" Berhasil logout", null);
    }

}

