package com.example.GestiondesRelations.controllers;

import com.example.GestiondesRelations.entities.Utilisateur;
import com.example.GestiondesRelations.entities.UtilisateurImage;
import com.example.GestiondesRelations.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.recupererTousLesUtilisateurs();
    }

    // Création d'un nouvel Utilisateur
    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.creerUtilisateurAvecRole(utilisateur.getNom(), utilisateur.getEmail(), utilisateur.getRole().getNom());
    }

    // Récupération d'un Utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.recupererUtilisateurParId(id);
        return ResponseEntity.ok(utilisateur);
    }

    // Association d'un rôle à un Utilisateur
    @PutMapping("/{utilisateurId}/role/{roleId}")
    public ResponseEntity<Utilisateur> assignRoleToUtilisateur(@PathVariable Long utilisateurId,
                                                               @PathVariable Long roleId) {
        Utilisateur utilisateur = utilisateurService.assignerRole(utilisateurId, roleId);
        return ResponseEntity.ok(utilisateur);
    }

    // Ajout d'une image à un Utilisateur
    @PostMapping("/{utilisateurId}/image")
    public ResponseEntity<UtilisateurImage> addImageToUtilisateur(@PathVariable Long utilisateurId, @RequestBody UtilisateurImage utilisateurImage) {
        UtilisateurImage image = utilisateurService.ajouterImageAUtilisateur(utilisateurId, utilisateurImage.getNomImage(), utilisateurImage.getCheminImage());
        return ResponseEntity.ok(image);
    }

    // Suppression d'un Utilisateur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateurById(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    // Suppression d'un Rôle par ID
    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long roleId) {
        utilisateurService.supprimerRole(roleId);
        return ResponseEntity.noContent().build();
    }

    // Suppression d'une image d'un Utilisateur
    @DeleteMapping("/{utilisateurId}/image/{imageId}")
    public ResponseEntity<Void> deleteImageFromUtilisateur(@PathVariable Long utilisateurId,
                                                           @PathVariable Long imageId) {
        utilisateurService.supprimerImageDeUtilisateur(utilisateurId, imageId);
        return ResponseEntity.noContent().build();
    }
}
