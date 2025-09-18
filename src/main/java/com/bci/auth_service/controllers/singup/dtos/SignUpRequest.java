package com.bci.auth_service.controllers.singup.dtos;

import com.bci.auth_service.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @ValidPassword
    private String password;


    private List<PhoneRequest> phones;

}
