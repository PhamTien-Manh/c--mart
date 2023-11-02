package edu.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payments")
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;

    @NotBlank
    @Column(columnDefinition = "nvarchar(255)")
    private String description;

    @OneToMany(mappedBy = "payment")
    private Set<Trip> trips;
}
