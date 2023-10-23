package edu.cmart.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
