package com.example.GestiondesRelations.entities;
import jakarta.persistence.*;

@Entity
public class UtilisateurImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomImage;

    private String cheminImage;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;


    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }
    public String getNomImage() {
        return nomImage ;
    }


    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
    public String getCheminImage() {
        return cheminImage ;
    }



    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

}
