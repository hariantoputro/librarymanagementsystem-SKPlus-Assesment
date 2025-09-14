package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.controller.managementuser;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.UserFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.UserRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.managementuser.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name = "User API")
public class UserController {

    private final UserService userService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody UserRequestRecord request) {
        userService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody UserRequestRecord request) {
        userService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }


    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN')")
    @Parameters({
            @Parameter(name = "page", description = "Page Number", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"), required = true),
            @Parameter(name = "size", description = "Size Per Page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"), required = true),
            @Parameter(name = "sort", description = "Sorting Data", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "modifiedDate,desc"), required = true)
    })

    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody UserFilterRecord filterRequest) {
        return BaseResponse.ok(null, userService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findById(@PathVariable String id) {

        return BaseResponse.ok(null, userService.findById(id));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> hardDelete(@PathVariable String id) {
        userService.delete(id);
        return BaseResponse.ok("Data berhasil dihapus", null);
    }

}
