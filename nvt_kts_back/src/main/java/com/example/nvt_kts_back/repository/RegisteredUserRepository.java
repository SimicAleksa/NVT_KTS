package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String>  {
    Optional<RegisteredUser> getByEmail(String email);
    Optional<RegisteredUser> getById(Long id);

    RegisteredUser findByEmail(String email);

    @Query("select r.tokens from RegisteredUser r where r.email =?1")
    Double findTokensByEmail(String email);

    @Modifying
    @Query("update RegisteredUser u set u.tokens=?2 where u.email =?1")
    void setTokens(String email, Double newTokens);
}
