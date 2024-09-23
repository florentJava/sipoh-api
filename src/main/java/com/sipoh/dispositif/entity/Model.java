package com.sipoh.dispositif.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Model {

    @Id
    private String id;

    @Column 
    private String nom;

    @Column 
    private String description;

    @Column
    private String prix;

    @OneToMany(
        mappedBy = "model"

    )
    private List<Dispositif> dispositifs;






    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }    
}
