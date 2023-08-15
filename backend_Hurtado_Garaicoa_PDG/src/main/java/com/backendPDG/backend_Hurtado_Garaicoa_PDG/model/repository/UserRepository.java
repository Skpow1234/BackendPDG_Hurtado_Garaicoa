package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

