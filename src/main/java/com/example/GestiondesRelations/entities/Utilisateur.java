package com.example.GestiondesRelations.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nom;
    @Column(unique=true, nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UtilisateurImage utilisateurImage;


    public void setNom(String nom) {
        this.nom = nom;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public void setRole(Role role) {
        this.role = role;
    }


    public long  getId() {
        return id ;
    }
    public String getNom() {
        return nom ;
    }
    public String getEmail() {
        return email ;
    }
    public Role getRole() {
        return role ;
    }
}
