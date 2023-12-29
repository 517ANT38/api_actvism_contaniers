package com.charity.activism.repositories;

import com.charity.activism.models.ActivismUser;
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
    @Query(value = "insert into roleuser(idrole,iduser) values(:idR, :id)", nativeQuery = true)
    void addRoleUser(@Param("idR") int  idRole,@Param("id") int id);

    @Query("from ActivismUser a left join fetch a.roles where a.login=:login")
    Optional<ActivismUser> findByLogin(@Param("login")String  login);

    boolean existsByLogin(String login);
}
