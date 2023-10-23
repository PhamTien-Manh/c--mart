package edu.cmart.model.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CityDto {

    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
}
