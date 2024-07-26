package com.study.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="username", unique=true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private boolean enabled;
}
