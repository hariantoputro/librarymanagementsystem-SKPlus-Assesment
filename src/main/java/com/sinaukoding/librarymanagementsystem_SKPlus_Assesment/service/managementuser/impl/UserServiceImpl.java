package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.managementuser.impl;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.mapper.managementuser.UserMapper;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app.SimpleMap;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.filter.UserFilterRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.request.UserRequestRecord;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.managementuser.UserRepository;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.managementuser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
//    private final PasswordEncoder passwordEncoder;


    @Override
    public void add(UserRequestRecord request) {
        // validasi mandatory
        validasiMandatory(request);

        // validasi data existing
        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException("Username [" + request.username() + "] sudah digunakan");
        }

        var user = userMapper.requestToEntity(request);

        user.setCreatedBy("admin");
//        user.setCreatedDate(LocalDateTime.now());
//        user.setModifiedDate(LocalDateTime.now());
        user.setPassword(request.password());
//        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    @Override
    public void edit(UserRequestRecord request) {
        // validasi mandatory
        validasiMandatory(request);

        var userExisting = userRepository.findById(request.id()).orElseThrow(() ->  new RuntimeException("Data user tidak ditemukan"));

        // validasi data existing
        if (userRepository.existsByEmailAndIdNot(request.email().toLowerCase(), request.id())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }
        if (userRepository.existsByUsernameAndIdNot(request.username().toLowerCase(),  request.id())) {
            throw new RuntimeException("Username [" + request.username() + "] sudah digunakan");
        }

        var user = userMapper.requestToEntity(request);
        user.setId(userExisting.getId());
        user.setPassword(request.password());
//        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }


    @Override
    public Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable) {
        return null;
    }

    @Override
    public SimpleMap findById(String id) {
        return null;
    }

    private void validasiMandatory(UserRequestRecord request) {
        if (request.nama() == null || request.nama().isEmpty()) {
            throw new RuntimeException("Nama tidak boleh kosong");
        }
        if (request.username() == null || request.username().isEmpty()) {
            throw new RuntimeException("Username tidak boleh kosong");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new RuntimeException("Email tidak boleh kosong");
        }
        if (request.password() == null || request.password().isEmpty()) {
            throw new RuntimeException("Email tidak boleh kosong");
        }
        if (request.status() == null) {
            throw new RuntimeException("Status tidak boleh kosong");
        }
        if (request.role() == null) {
            throw new RuntimeException("Role tidak boleh kosong");
        }
    }
}
