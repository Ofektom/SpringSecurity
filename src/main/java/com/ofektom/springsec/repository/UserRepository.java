package com.ofektom.springsec.repository;


import com.ofektom.springsec.entities.Users;
import com.ofektom.springsec.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Users findByRole(Role role);
}
