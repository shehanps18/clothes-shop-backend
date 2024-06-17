package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RolesEntity {
    @Id
    @Column(name = "Role_ID",nullable = false)
    private int roleId;

    @Column(name = "Role_Name", nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "role")
    Set<UserEntity> user = new HashSet<>();
}
