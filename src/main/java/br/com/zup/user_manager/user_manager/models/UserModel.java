package br.com.zup.user_manager.user_manager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import java.util.Set;

@Entity
@Data
@Table(name="users")
public class UserModel {
    @Id
    @UuidGenerator
    private String uuid;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users-roles",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
            )
    private Set<Role> roles;


}
