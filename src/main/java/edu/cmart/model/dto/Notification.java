package edu.cmart.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Notification {

    private String content;
    private String sender;
}
