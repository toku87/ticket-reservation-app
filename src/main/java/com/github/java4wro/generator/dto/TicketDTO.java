package com.github.java4wro.generator.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TicketDTO {

    private String row;
    private String seat;
    private BigDecimal price;
    private String ticketId;
}
