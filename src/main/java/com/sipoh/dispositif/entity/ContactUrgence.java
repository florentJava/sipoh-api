package com.sipoh.dispositif.entity;



import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.sipoh.dispositif.entity.enumeration.ContactProfil;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContactUrgence {

    @Id
    private String id;


    @Column
    private Integer numero;

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

}