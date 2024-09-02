package com.sipoh.dispositif.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder
public class MessageRequest {

    private String to;
    private String message;
    
}
