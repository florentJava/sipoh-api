package com.sipoh.dispositif.entity;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.sipoh.dispositif.entity.enumeration.DispositifStatut;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Dispositif {
    
    // ID du dispositif. Il est atotribue par le fabriquant
    @Id
    private String id;

    // Date de fabrication du dispositif
    @Column
    private LocalDateTime dateFabrication;

    // Renseigne si le dispositif est en service , hors servive ou en attente
    @Enumerated(EnumType.STRING)
    private DispositifStatut statut;

    // ID du proprietaire du dispositif

    
    @Column 
    private String utilisateurId;

    // @ManyToOne()
    // @JoinColumn(name="utilisateur_id")
    // private UserEntity user;

    @ManyToOne(        
    )
    @JoinColumn(name="model")
    private Model model;

    @ManyToOne(        
    )
    @JoinColumn(name="fournisseur")
    private Fournisseur fournisseur;

    
    @OneToMany(
        mappedBy = "dispositif",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<EnregistrementAudio> audios;
    
    // list des contacts d'urgences
    @OneToMany(
        mappedBy = "dispositif",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ContactUrgence> contacts;


    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
