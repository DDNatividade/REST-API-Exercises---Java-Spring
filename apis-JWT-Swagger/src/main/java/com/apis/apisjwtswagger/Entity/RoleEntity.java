package com.apis.apisjwtswagger.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="role")

//Implementamos GrantedAuthority,
//para que los roles sean reconocidos por Spring Security como permisos de acceso.
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "role")
    private List<UsersEntity> usersEntityList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleList")
    private Set<PermissionsEntity> permissionList;

    // 👇 importante para que Spring reconozca el rol como autoridad
    @Override
    public String getAuthority() {
        return role.name();
    }
}
