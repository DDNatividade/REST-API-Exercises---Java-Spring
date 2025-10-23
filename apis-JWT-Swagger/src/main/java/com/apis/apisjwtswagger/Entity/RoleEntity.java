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
@Table(name="role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name="users_with_role")
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "role"
    )
    List<UsersEntity> usersEntityList;

    @Column(name="role_permissions")
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roleList")
    private Set<PermissionsEntity> permissionList;



}
