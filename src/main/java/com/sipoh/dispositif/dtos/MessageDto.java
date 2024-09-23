package com.sipoh.dispositif.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MessageDto {

    private String to;
    private String subject;
    private String body;
    
}
