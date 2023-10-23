package edu.cmart.model.dto;

import edu.cmart.entity.enums.TypeMessage;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class MessageDto {
    private Long id;
    private String content;
    private TypeMessage typeMessage;
    private Boolean isRead;
    private Date time;

    private Long receiverId;
    private Long senderId;
    private Long conversationId;

}
