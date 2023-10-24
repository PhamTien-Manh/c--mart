package edu.cmart.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class AddressDto {

    private Long id;
    private String ApartmentNumber;

    private Long wardId;
    private Long roleUserId;
}
