package edu.cmart.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private Double fixedPrice;

    @Column(nullable = false)
    private Double priceFluctuates;

    @Column(nullable = false)
    private Double timeFluctuates;

    @Column(nullable = false)
    private Double fixedKm;

    @Column(nullable = false)
    private Boolean isActived;

    @OneToMany(mappedBy = "serviceCar")
    private Set<ServiceOfCity> serviceOfCities;

    @OneToMany(mappedBy = "serviceCar")
    private Set<Vehicle> vehicles;

    @ManyToOne
    @JoinColumn(name = "typeVehicleId")
    private TypeVehicle typeVehicle;
}
