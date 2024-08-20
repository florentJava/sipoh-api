package com.sipoh.dispositif.entity;




import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnregistrementAudio {
    
    @Id
    private String id;

    @Column
    private String audioUrl;

    @Column
    private LocalDateTime dateTime;


    @Column
    private String lattitude;

    @Column 
    private String altitude;

    @Column
    private String longitude;

    @ManyToOne(        
    cascade = { 
    CascadeType.PERSIST, 
    CascadeType.MERGE 
    })
    @JoinColumn(name="id_dispositif")
    private Dispositif dispositif;


}
