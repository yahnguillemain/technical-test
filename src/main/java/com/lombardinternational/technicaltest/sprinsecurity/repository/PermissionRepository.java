package com.lombardinternational.technicaltest.sprinsecurity.repository;

import com.lombardinternational.technicaltest.sprinsecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findByUserLogin(String userLogin);
}
