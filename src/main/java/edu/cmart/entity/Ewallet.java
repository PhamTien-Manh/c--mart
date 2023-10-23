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
@Table(name = "e_wallets")
@Builder
public class Ewallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double totalMoney;

    @Column(columnDefinition = "varchar(50) not null")
    private String bankName;

    @Column(columnDefinition = "varchar(19) not null unique")
    private String bankNumber;

    @Column(nullable = false)
    private Boolean isActivated;

    @OneToOne
    @JoinColumn(name = "roleAccount")
    private Role roleAccount;
}
