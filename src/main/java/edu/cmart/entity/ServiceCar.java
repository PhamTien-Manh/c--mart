package edu.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "serviceCars")
@Builder
public class ServiceCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Column(nullable = false)
    private Double fixedPrice;

    @Min(0)
    @Column(nullable = false)
    private Double priceFluctuates;

    @Min(0)
    @Column(nullable = false)
    private Double timeFluctuates;

    @Min(0)
    @Column(nullable = false)
    private Double fixedKm;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActived = true;

    @OneToMany(mappedBy = "serviceCar")
    private Set<ServiceOfCity> serviceOfCities;

    @OneToMany(mappedBy = "serviceCar")
    private Set<Vehicle> vehicles;

    @ManyToOne
    @JoinColumn(name = "typeVehicleId")
    private TypeVehicle typeVehicle;
}
