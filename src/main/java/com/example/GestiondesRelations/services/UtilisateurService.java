package com.example.GestiondesRelations.services;

import com.example.GestiondesRelations.entities.Role;
import com.example.GestiondesRelations.entities.Utilisateur;
import com.example.GestiondesRelations.entities.UtilisateurImage;
import com.example.GestiondesRelations.repositories.RoleRepository;
import com.example.GestiondesRelations.repositories.UtilisateurImageRepository;
import com.example.GestiondesRelations.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final UtilisateurImageRepository utilisateurImageRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, UtilisateurImageRepository utilisateurImageRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.utilisateurImageRepository = utilisateurImageRepository;
    }

    public List<Utilisateur> recupererTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Transactional
    public Utilisateur creerUtilisateurAvecRole(String nom, String email, String nomRole) {
        // Vérifier si le rôle existe
        Role role = roleRepository.findByNom(nomRole)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + nomRole));

        // Créer un nouvel utilisateur avec le rôle
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setRole(role);

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur recupererUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + id));
    }

    @Transactional
    public Utilisateur assignerRole(Long utilisateurId, Long roleId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + utilisateurId));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + roleId));

        utilisateur.setRole(role);
        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public UtilisateurImage ajouterImageAUtilisateur(Long utilisateurId, String nomImage, String cheminImage) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + utilisateurId));

        // Créer l'image et l'associer à l'utilisateur
        UtilisateurImage utilisateurImage = new UtilisateurImage();
        utilisateurImage.setNomImage(nomImage);
        utilisateurImage.setCheminImage(cheminImage);
        utilisateurImage.setUtilisateur(utilisateur);

        // Sauvegarder l'image
        return utilisateurImageRepository.save(utilisateurImage);
    }

    @Transactional
    public void supprimerUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé : " + id);
        }
        utilisateurRepository.deleteById(id);
    }


    @Transactional
    public void supprimerRole(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new RuntimeException("Rôle non trouvé : " + roleId);
        }
        roleRepository.deleteById(roleId);
    }


    @Transactional
    public void supprimerImageDeUtilisateur(Long utilisateurId, Long imageId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + utilisateurId));

        UtilisateurImage utilisateurImage = utilisateurImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image non trouvée : " + imageId));

        if (!utilisateurImage.getUtilisateur().equals(utilisateur)) {
            throw new RuntimeException("L'image n'est pas associée à cet utilisateur.");
        }

        utilisateurImageRepository.delete(utilisateurImage);
    }


    public List<Utilisateur> recupererUtilisateursParRole(String nomRole) {
        return utilisateurRepository.findByRole_Nom(nomRole);
    }
}


