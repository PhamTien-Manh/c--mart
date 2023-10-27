package edu.cmart.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class LoginRequest {
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
}
