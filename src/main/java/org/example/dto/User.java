package org.example.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private  int userId;
    private String name;
    private String email;
    private String password;
    private String address;
    private String gender;
    private String about;
    private String phone;
    private Date date;
    private boolean active;
}
