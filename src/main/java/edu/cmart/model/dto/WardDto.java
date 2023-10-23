package edu.cmart.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class WardDto {
    private Long id;
    private String name;

    private Long districtId;
}
