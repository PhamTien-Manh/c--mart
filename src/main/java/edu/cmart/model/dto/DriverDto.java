package edu.cmart.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class DriverDto {
    private AccountDto account;
    private RoleDto role;
    private VehicleDto vehicle;
}
