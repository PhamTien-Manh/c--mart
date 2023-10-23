package edu.cmart.entity;

import edu.cmart.entity.enums.Gender;
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
@Table(name = "accounts")
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String fullname;

    @Column(columnDefinition = "varchar(50) unique")
    private String email;

    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @Column(columnDefinition = "varchar(6) not null")
    private String quickPassword;

    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(20) unique")
    private String citizenIdentificationCard;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(columnDefinition = "nvarchar(255)")
    private String image;

    @Column(nullable = false)
    private Boolean isActivated;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @OneToMany(mappedBy = "account")
    private Set<Role> roles;
}
