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
@Table(name = "addresses")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(100) not null")
    private String ApartmentNumber;

    @ManyToOne
    @JoinColumn(name = "wardId")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "roleUserId")
    private Role roleUser;
}
