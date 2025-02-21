package br.com.zup.user_manager.user_manager.repositories;

import br.com.zup.user_manager.user_manager.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
