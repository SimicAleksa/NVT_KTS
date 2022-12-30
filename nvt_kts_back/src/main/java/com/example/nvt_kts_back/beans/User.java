package com.example.nvt_kts_back.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "my_users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String city;
    @Column
    private String phone;
    @Column
    private Boolean profileActivated;
    @Column
    private String picture;
    @Column
    private Boolean isBlocked;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public User(String email, String password, String name, String surname, String city, String phone,
                Boolean profileActivated, String picture, Boolean isBlocked, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.phone = phone;
        this.profileActivated = profileActivated;
        this.picture = picture;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role r = this.role;
        return new ArrayList<Role>() {
            {add(r);}
        };
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.profileActivated;
    }

}
