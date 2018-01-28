package com.github.java4wro.generator.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {

    private String eventName;
    private LocalDateTime eventDateAndTime;


}
