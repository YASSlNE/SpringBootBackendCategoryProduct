package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.ERole;
import com.example.springbootbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
