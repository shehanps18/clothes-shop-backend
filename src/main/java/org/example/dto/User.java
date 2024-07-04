package org.example.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.example.entity.CartEntity;
import org.example.entity.RolesEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "userId")
public class User {
    private  Long userId;
    private String name;
    private String email;
    private String password;
    private String address;
    private String gender;
    private String about;
    private String phone;
    private Date date;
    private boolean active;
    private Cart cart;
    Set<Roles> role = new HashSet<>();
}
