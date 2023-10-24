package edu.cmart.model.dto;

import lombok.*;

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
