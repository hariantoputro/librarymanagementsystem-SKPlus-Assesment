package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.repository.managementuser;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.entity.managementuser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}