package com.secure.notes.repositories;

import com.secure.notes.entity.AppRole;
import com.secure.notes.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(AppRole role);
}
