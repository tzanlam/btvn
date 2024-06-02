package demoJWT.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "`user`")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
