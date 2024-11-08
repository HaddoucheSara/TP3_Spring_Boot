package com.example.GestiondesRelations.repositories;


import com.example.GestiondesRelations.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Recherche d'un utilisateur par email
    Optional<Utilisateur> findByEmail(String email);

    // Récupération des utilisateurs associés à un rôle spécifique
    List<Utilisateur> findByRole_Nom(String roleName);
}

