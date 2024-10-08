package com.sipoh.dispositif.entity;



import java.util.UUID;

import com.sipoh.dispositif.entity.enumeration.ContactProfil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContactUrgence {

    @Id
    private String id;


    @Column
    private String numero;

    @Column
    private String nom;

    @Column
    private String prenom;

    // Le contact d'urgence est il votre pere , mere etc ?
    @Enumerated(EnumType.STRING)
    private ContactProfil profil; 

    @ManyToOne(        
        cascade = { 
        CascadeType.PERSIST, 
        CascadeType.MERGE 
        })
    @JoinColumn(name="id_dispositif")
    private Dispositif dispositif;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

}