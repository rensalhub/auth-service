package com.bci.auth_service.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
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
@Entity(name = "app_user")
public class UserEntity {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    private Boolean isActive;

    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PhoneEntity> phones;

}
