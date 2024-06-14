package org.example.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldNameConstants
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  int userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String gender;

    private String about;

    @Column(nullable = false,length = 10)
    private String phone;

    @Column(name = "CreateAt")
    private Date date;

    private boolean active;
}
