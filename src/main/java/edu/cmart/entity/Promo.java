package edu.cmart.entity;

import edu.cmart.entity.enums.TypeDiscount;
import jakarta.persistence.*;
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

    @Column(columnDefinition = "nvarchar(100) not null")
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String image;

    @Column(columnDefinition = "nvarchar(2550) not null")
    private String description;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double minDiscount;

    @Column(nullable = false)
    private Double maxDiscount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endTime;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeDiscount typeDiscount;

    @OneToMany(mappedBy = "promo")
    private Set<Trip> trips;

}
