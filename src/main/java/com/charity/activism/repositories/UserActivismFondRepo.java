package com.charity.activism.repositories;

import com.charity.activism.models.Activism;
import com.charity.activism.models.ActivismUser;
import com.charity.activism.models.Fond;
import com.charity.activism.models.UserActivismFond;
import com.charity.activism.util.DateAndSumHours;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivismFondRepo extends JpaRepository<UserActivismFond,Integer> {

    List<UserActivismFond> findByUser(ActivismUser a);

    List<UserActivismFond> findByFond(Fond fond);

    List<UserActivismFond> findByActivism(Activism a);

    List<UserActivismFond> findByUserAndDate(ActivismUser a,Date date);

    Optional<UserActivismFond> findByUserAndId(ActivismUser u, int id);

    @Query("select  uaf.user from UserActivismFond uaf where uaf.activism=:a order by uaf.countHours limit 10")
    List<ActivismUser> findFirst10ByActivismOrderByActivismCountOurs(@Param("a") Activism a);

    @Modifying
    @Query("update UserActivismFond uaf set uaf.done=true where uaf.id=:id and uaf.user=:a")
    void changeStatusDone(@Param("id") int id,@Param("a") ActivismUser a);

    @Query(
        """
            select sum(uaf.activism.pay*uaf.countHours) 
            from UserActivismFond uaf 
            where uaf.done and uaf.user=:u and not uaf.paid
        """
            
    )
    double sumChartity(@Param("u") ActivismUser u);

    @Modifying
    @Query("update UserActivismFond uaf set uaf.paid=true where uaf.id=:id and uaf.user=:a and uaf.done")
    void changeStatusPaid(@Param("id") int id,@Param("a") ActivismUser a);

    @Query("""
            select new com.charity.activism.util.DateAndSumHours(uaf.date, sum(uaf.countHours)) 
            from UserActivismFond uaf 
            where uaf.user=:u and uaf.date between :s and :e
            group by uaf.date  
        """
    )
    List<DateAndSumHours> statisticUserActivismIntervalDate(@Param("u") ActivismUser u,@Param("s") Date start,@Param("e") Date end);

}
