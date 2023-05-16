package com.lombardinternational.technicaltest.sprinsecurity.repository;

import com.lombardinternational.technicaltest.sprinsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
