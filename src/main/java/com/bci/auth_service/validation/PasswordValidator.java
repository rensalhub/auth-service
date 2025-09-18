package com.bci.auth_service.validation;

import com.bci.auth_service.config.PasswordRegexProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private final PasswordRegexProperties passwordRegexProperties;

    private Pattern pattern;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null)
            return false;
        if (pattern == null) {
            pattern = Pattern.compile(passwordRegexProperties.getRegex());
        }
        return pattern.matcher(value).matches();
    }

}

