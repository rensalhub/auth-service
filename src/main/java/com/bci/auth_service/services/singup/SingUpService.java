package com.bci.auth_service.services.singup;

import com.bci.auth_service.controllers.singup.dtos.SignUpRequest;
import com.bci.auth_service.controllers.singup.dtos.SignUpResponse;

public interface SingUpService {

    SignUpResponse signUpUser(SignUpRequest signUpRequest);

}
