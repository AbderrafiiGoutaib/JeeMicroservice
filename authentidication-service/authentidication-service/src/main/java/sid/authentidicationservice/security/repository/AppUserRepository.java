package sid.authentidicationservice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sid.authentidicationservice.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
