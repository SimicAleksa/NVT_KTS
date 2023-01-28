package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String>  {
    Optional<RegisteredUser> getByEmail(String email);

    RegisteredUser findByEmail(String email);

    @Query("select r.tokens from RegisteredUser r where r.email =?1")
    Double findTokensByEmail(String email);

    @Modifying
    @Query("update RegisteredUser u set u.tokens=?2 where u.email =?1")
    void setTokens(String email, Double newTokens);

    Optional<RegisteredUser> getById(Long id);

    @Query(
            "SELECT u FROM RegisteredUser u " +
                    "LEFT JOIN FETCH u.favouriteRoutes " +
                        "WHERE u.id = :userId"
    )
    Optional<RegisteredUser> getRegUserWithFavouriteRoutesByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("update RegisteredUser u set u.name=?1, u.surname=?2, u.city=?3, u.phone=?4 where u.email =?5")
    void updateData(String name, String surname, String city, String phone, String email);
}
