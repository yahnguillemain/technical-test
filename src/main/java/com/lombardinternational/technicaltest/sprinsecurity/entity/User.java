package com.lombardinternational.technicaltest.sprinsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="USERS")
public class User {

    @Id
    private String login;
    @Column(name = "PASSWD")
    private String password;
    private Boolean enabled;

    private LocalDate birthDate;
}
