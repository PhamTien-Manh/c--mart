package edu.cmart.model.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class LoginRequest {
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
}
