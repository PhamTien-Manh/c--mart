package edu.cmart.entity;

import edu.cmart.entity.enums.TypeMessage;
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
@Table(name = "messages")
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(2550) not null")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMessage typeMessage;

    @Column(nullable = false)
    private Boolean isRead;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private Role receiver;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private Role sender;

    @ManyToOne
    @JoinColumn(name = "conversationId")
    private Conversation conversation;

}
