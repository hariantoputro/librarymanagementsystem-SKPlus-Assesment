package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.managementuser;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.UserFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.UserRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.managementuser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("find-all")
//    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody UserFilterRecord filterRequest) {
        return BaseResponse.ok(null, userService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findById(@PathVariable String id) {

        return BaseResponse.ok(null, userService.findById(id));
    }

    @DeleteMapping("hard-delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> hardDelete(@PathVariable String id) {
        userService.hardDelete(id);
        return BaseResponse.ok("Data berhasil dihapus", null);
    }
}
