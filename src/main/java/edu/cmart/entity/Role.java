package edu.cmart.entity;

import edu.cmart.entity.enums.TypeRoles;
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
@Table(name = "roles")
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeRoles typeRoles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany(mappedBy = "roleUser")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "firstMember")
    private Set<Conversation> conversationsFirst;

    @OneToMany(mappedBy = "secondMember")
    private Set<Conversation> conversationsSecond;

    @OneToMany(mappedBy = "receiver")
    private Set<Message> messagesReceiver;

    @OneToMany(mappedBy = "sender")
    private Set<Message> messagesSender;

    @OneToOne(mappedBy = "roleDriver")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "roleUser")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "roleAccount")
    private Set<Transaction> transactions;

    @OneToOne(mappedBy = "roleAccount")
    private Ewallet ewallet;

    @OneToMany(mappedBy = "roleUser")
    private Set<Trip> tripsUser;

    @OneToMany(mappedBy = "roleDriver")
    private Set<Trip> tripsDriver;

    public Role(TypeRoles typeRoles, Account accountNew) {
        this.typeRoles = typeRoles;
        this.account = accountNew;
    }
}
