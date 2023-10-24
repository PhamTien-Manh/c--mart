package edu.cmart.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ConversationDto {

    private Long id;
    private Boolean status;

    private Long firstMemberId;
    private Long secondMemberId;

}

