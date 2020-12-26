package sid.authentidicationservice.security.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    String username ;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password ;
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<AppRole> appRoles = new ArrayList<>();
}
