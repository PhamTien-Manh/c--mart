package edu.cmart.model.dto;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class EwalletDto {

    private Long id;
    private Double totalMoney;
    private String bankName;
    private String bankNumber;
    private Boolean isActivated;

    private Long roleAccountId;
}
