package com.bci.auth_service.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bci.auth_service.config.PasswordRegexProperties;
import jakarta.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

class PasswordValidatorTest {

    private PasswordValidator validator;

    private PasswordRegexProperties regexProperties;

    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {

        regexProperties = new PasswordRegexProperties();
        regexProperties.setRegex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,12}$");
        validator = new PasswordValidator(regexProperties);
        ReflectionTestUtils.setField(validator, "passwordRegexProperties", regexProperties);
        context = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    @DisplayName("Verifica que contraseñas válidas según el regex sean aceptadas.")
    void testValidPassword() {

        assertTrue(validator.isValid("Abcdef12", context));
        assertTrue(validator.isValid("A1bcdefg", context));
    }

    @Test
    @DisplayName("Verifica que una contraseña sin mayúsculas sea rechazada.")
    void testInvalidPassword_NoUppercase() {

        assertFalse(validator.isValid("abcdef12", context));
    }

    @Test
    @DisplayName("Verifica que una contraseña sin minúsculas sea rechazada.")
    void testInvalidPassword_NoLowercase() {

        assertFalse(validator.isValid("ABCDEFG1", context));
    }

    @Test
    @DisplayName("Verifica que una contraseña sin dígitos sea rechazada.")
    void testInvalidPassword_NoDigit() {

        assertFalse(validator.isValid("Abcdefgh", context));
    }

    @Test
    @DisplayName("Verifica que una contraseña demasiado corta sea rechazada.")
    void testInvalidPassword_TooShort() {

        assertFalse(validator.isValid("Abc1", context));
    }

    @Test
    @DisplayName("Verifica que una contraseña demasiado larga sea rechazada.")
    void testInvalidPassword_TooLong() {

        assertFalse(validator.isValid("AbcdefghijkL1", context));
    }

    @Test
    @DisplayName("Verifica que una contraseña nula sea rechazada.")
    void testNullPassword() {

        assertFalse(validator.isValid(null, context));
    }

}
