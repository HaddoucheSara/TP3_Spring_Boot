package com.example.GestiondesRelations.repositories;


import com.example.GestiondesRelations.entities.UtilisateurImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurImageRepository extends JpaRepository<UtilisateurImage, Long> {
    UtilisateurImage findByUtilisateurId(Long utilisateurId);
}
