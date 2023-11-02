package edu.cmart.entity;

import edu.cmart.entity.enums.TypeDiscount;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "promos")
@Builder
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "nvarchar(100) not null")
    private String name;

    @NotBlank
    @Column(columnDefinition = "nvarchar(255)")
    private String image;

    @NotBlank
    @Column(columnDefinition = "nvarchar(2550) not null")
    private String description;

    @Min(1)
    @Column(nullable = false)
    private Double discount;

    @Min(0)
    @Column(nullable = false)
    private Double minDiscount;

    @Min(0)
    @Column(nullable = false)
    private Double maxDiscount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endTime;

    @Min(1)
    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeDiscount typeDiscount;

    @OneToMany(mappedBy = "promo")
    private Set<Trip> trips;

}
