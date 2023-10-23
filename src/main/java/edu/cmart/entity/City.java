package edu.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cities")
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;

    @NotBlank
    @Column(columnDefinition = "nvarchar(100) not null")
    private String description;

    @OneToMany(mappedBy = "city")
    private Set<District> districts;

    @OneToMany(mappedBy = "city")
    private Set<ServiceOfCity> serviceOfCities;
}
