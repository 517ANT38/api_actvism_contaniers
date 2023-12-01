package com.charity.activism.repositories;

import com.charity.activism.models.ActivismUser;
import com.charity.activism.models.Role;
import com.charity.activism.models.Subdivision;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivismUserRepo extends JpaRepository<ActivismUser,Integer> {

    List<ActivismUser> findBySubdivision(Subdivision s);
   
    @Query("select  a from ActivismUser a order by a.userActivismFonds.size() limit 10")
    List<ActivismUser> findFirst10OrderByCountUserActivismFondsAsc();

    @Modifying
    @Query("update ActivismUser a set a.role=:r where a.id=:id")
    void updateRoleUser(@Param("r") Role r,@Param("id") int id);

    Optional<ActivismUser> findByLogin(String  login);

    boolean existsByLogin(String login);
}
