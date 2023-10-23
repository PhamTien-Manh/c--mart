package edu.cmart.entity;

import edu.cmart.entity.enums.TypeTrip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "trips")
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double totalMoney;

    @Column(nullable = false)
    private Double distance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date minTime;

    @Column(columnDefinition = "nvarchar(255) not null")
    private String startLocation;

    @Column(columnDefinition = "nvarchar(255) not null")
    private String finishLocation;

    @Column
    private Boolean isScheduled;

    @Column(nullable = false)
    private TypeTrip typeTrip;

    @OneToOne(mappedBy = "trip")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "promoId")
    private Promo promo;

    @ManyToOne
    @JoinColumn(name = "driverId")
    private Role roleDriver;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Role roleUser;

}
