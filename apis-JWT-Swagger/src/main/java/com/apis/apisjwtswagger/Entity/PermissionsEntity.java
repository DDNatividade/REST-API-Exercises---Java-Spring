package com.apis.apisjwtswagger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="permissions")
public class PermissionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionsEnum name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            //Definimos la tabla intermedia
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_permissions"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")

            )

    private Set<RoleEntity> roleList;


}
