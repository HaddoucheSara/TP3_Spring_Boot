package com.example.GestiondesRelations.entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurs;


    public String getNom() {
        return nom;
    }
}
