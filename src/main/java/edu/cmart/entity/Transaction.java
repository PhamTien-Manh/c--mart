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
@Table(name = "transactions")
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double AmountOfMoney;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private Boolean isSuccess;

    @ManyToOne
    @JoinColumn(name = "roleAccountId")
    private Role roleAccount;

    @ManyToOne
    @JoinColumn(name = "e_walletId")
    private Ewallet ewallet;

}
