package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.managementuser;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.UserRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.managementuser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("save")
//    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody UserRequestRecord request) {
        userService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
//    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody UserRequestRecord request) {
        userService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }
}
