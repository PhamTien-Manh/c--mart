package edu.cmart.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DistrictDto {

    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Long cityId;
}
