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
@Table(name = "conversations")
@Builder
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "conversation")
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name = "firstMemberId")
    private Role firstMember;

    @ManyToOne
    @JoinColumn(name = "secondMemberId")
    private Role secondMember;

}

