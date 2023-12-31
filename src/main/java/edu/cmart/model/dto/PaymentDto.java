package edu.cmart.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Getter
@Setter
public class PaymentDto {
    private Long id;
    private String name;
    private String description;
}
