package com.bci.auth_service.services.singup;

import com.bci.auth_service.commons.utils.JwtUtil;
import com.bci.auth_service.controllers.singup.dtos.SignUpRequest;
import com.bci.auth_service.controllers.singup.dtos.SignUpResponse;
import com.bci.auth_service.entities.PhoneEntity;
import com.bci.auth_service.entities.UserEntity;
import com.bci.auth_service.errors.EmailAlreadyExistsException;
import com.bci.auth_service.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SingUpServiceImpl implements SingUpService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Override
    public SignUpResponse signUpUser(SignUpRequest signUpRequest) {

        this.validateEmailNotExists(signUpRequest.getEmail());
        var nowTime = LocalDateTime.now();
        UserEntity userEntity = this.buildUserEntity(signUpRequest, nowTime);
        if (signUpRequest.getPhones() != null && !signUpRequest.getPhones().isEmpty()) {
            List<PhoneEntity> phoneEntities = this.buildPhoneEntities(signUpRequest, userEntity);
            userEntity.setPhones(phoneEntities);
        }
        this.saveUserAndToken(userEntity);
        return this.buildSignUpResponse(userEntity);
    }

    private void validateEmailNotExists(String email) {

        var userByEmail = this.userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya registrado");
        }
    }

    private UserEntity buildUserEntity(SignUpRequest signUpRequest, LocalDateTime nowTime) {

        return UserEntity.builder()
            .id(UUID.randomUUID().toString())
            .name(signUpRequest.getName())
            .email(signUpRequest.getEmail())
            .password(signUpRequest.getPassword())
            .created(nowTime)
            .modified(nowTime)
            .lastLogin(nowTime)
            .isActive(true)
            .build();
    }

    private List<PhoneEntity> buildPhoneEntities(SignUpRequest signUpRequest,
        UserEntity userEntity) {

        return signUpRequest.getPhones().stream()
            .map(phoneReq -> PhoneEntity.builder()
                .number(phoneReq.getNumber())
                .cityCode(phoneReq.getCitycode())
                .countryCode(phoneReq.getContrycode())
                .user(userEntity)
                .build())
            .toList();
    }

    private void saveUserAndToken(UserEntity userEntity) {

        this.userRepository.save(userEntity);
        String jwt = jwtUtil.generateToken(userEntity.getId(), userEntity.getEmail());
        userEntity.setToken(jwt);
        this.userRepository.save(userEntity);
    }

    private SignUpResponse buildSignUpResponse(UserEntity userEntity) {

        return SignUpResponse.builder()
            .id(userEntity.getId())
            .created(userEntity.getCreated().toString())
            .modified(userEntity.getModified().toString())
            .lastLogin(userEntity.getLastLogin().toString())
            .token(userEntity.getToken())
            .isActive(userEntity.getIsActive())
            .build();
    }

}
