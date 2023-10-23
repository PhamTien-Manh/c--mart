package edu.cmart.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private Double AmountOfMoney;
    private String date;
    private Boolean isSuccess;
    private Long roleAccountId;

    private Long ewalletId;

}
