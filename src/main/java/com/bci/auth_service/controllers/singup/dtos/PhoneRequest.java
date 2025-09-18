package com.bci.auth_service.controllers.singup.dtos;

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
public class PhoneRequest {

    private String number;

    private String citycode;

    private String contrycode;

}
