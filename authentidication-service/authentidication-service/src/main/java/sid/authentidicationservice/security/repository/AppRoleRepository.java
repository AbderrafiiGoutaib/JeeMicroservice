package sid.authentidicationservice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sid.authentidicationservice.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findAppRoleName(String roleName);
}
