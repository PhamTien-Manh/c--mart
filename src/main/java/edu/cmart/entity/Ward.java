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
@Table(name = "wards")
@Builder
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;

    @OneToMany(mappedBy = "ward")
    private Set<Address> addresses;
}
