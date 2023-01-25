package com.example.nvt_kts_back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;


    public Role(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
}
