package edu.cmart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicles")
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(11) not null")
    private String licensePlate;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String description;

    @Column(columnDefinition = "nvarchar(255) not null")
    private String image;

    @ManyToOne
    @JoinColumn(name = "serviceCarId")
    private ServiceCar serviceCar;

    @OneToOne
    @JoinColumn(name = "driverId")
    private Role roleDriver;
}
