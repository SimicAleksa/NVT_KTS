package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>  {
    Optional<Role> getByName(final String name);
}
