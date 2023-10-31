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
@Table(name = "districts")
@Builder
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @OneToMany(mappedBy = "district")
    private Set<Ward> wards;
}
