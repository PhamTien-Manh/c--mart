package edu.cmart.model.dto;
import lombok.*;


@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TypeVehicleDto {

    private Long id;
    @NonNull
    private String name;
    private String description;
}
