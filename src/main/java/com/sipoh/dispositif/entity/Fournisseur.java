package com.sipoh.dispositif.entity;

import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Fournisseur {

    @Id
    private String id;

    @Column
    private String nom;

    @Column
    private String email;

    @Column 
    private String tel;

    @OneToMany(
    mappedBy = "fournisseur"
    )
    private List<Dispositif> dispositifs;



    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
    
}
