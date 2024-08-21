package com.sipoh.dispositif.entity;


import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.time.LocalDateTime;
import com.sipoh.dispositif.entity.enumeration.DispositifStatut;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dispositif {
    
    // ID du dispositif. Il est attribue par le fabriquant
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
}
