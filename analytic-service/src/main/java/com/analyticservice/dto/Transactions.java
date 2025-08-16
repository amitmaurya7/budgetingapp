package com.analyticservice.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    private Long id;
    private Double amount;
    private String category;
    private String note;
    private String userEmail;
    private LocalDate date;
}