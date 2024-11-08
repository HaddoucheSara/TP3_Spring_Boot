package com.example.GestiondesRelations.repositories;


import com.example.GestiondesRelations.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(String nom);
}
