package com.sipoh.dispositif.entity;

import java.util.List;
import java.util.UUID;

import com.sipoh.dispositif.entity.enumeration.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString

@Table(name = "dbuser")
@Entity
@RequiredArgsConstructor
public class UserEntity {

    @Id
    @Column (name = "id")
    private String id;

    @Column ()
    private String email;

    @Column ()
    private String password;

    @Column
    private String nom;

    @Column
    private String prenom;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;




    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
    
}
