package com.charity.activism.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.charity.activism.exceptions.ActivismUserNotFoundException;
import com.charity.activism.exceptions.FondNotFoundException;
import com.charity.activism.exceptions.UserActivismFondNotFoundException;
import com.charity.activism.models.ActivismUser;
import com.charity.activism.models.UserActivismFond;
import com.charity.activism.repositories.ActivismRepo;
import com.charity.activism.repositories.ActivismUserRepo;
import com.charity.activism.repositories.FondRepo;
import com.charity.activism.repositories.UserActivismFondRepo;
import com.charity.activism.util.DateAndSumHours;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserActivismFondService {


    private final UserActivismFondRepo uActivismFondRepo;
    private final ActivismRepo activismRepo;
    private final FondRepo fondRepo;
    private final ActivismUserRepo aUserRepo;
    private final ActionService aService;

    public List<UserActivismFond> getAllByUser(int actUserId){
        
        var u = aUserRepo.findById(actUserId)
                        .orElseThrow(ActivismUserNotFoundException::new);
        
        return uActivismFondRepo.findByUser(u);
    }

    public List<UserActivismFond> getAllByFond(int fondId){

        var f = fondRepo.findById(fondId)
                        .orElseThrow(FondNotFoundException::new);

        return uActivismFondRepo.findByFond(f);
    }

    public List<UserActivismFond> getAllByActivism(int actId){
        
        var a = activismRepo.findById(actId)
                        .orElseThrow(ActivismUserNotFoundException::new);
        
        return uActivismFondRepo.findByActivism(a);
    }

    public List<UserActivismFond> getAllByUserAndDate(int actUserId, Date date){

        var u = aUserRepo.findById(actUserId)
                        .orElseThrow(ActivismUserNotFoundException::new);

        return uActivismFondRepo.findByUserAndDate(u, date);
    }


    public UserActivismFond getByUserAndId(int actUserId, int id){

        var u = aUserRepo.findById(actUserId)
                        .orElseThrow(ActivismUserNotFoundException::new);

        return uActivismFondRepo.findByUserAndId(u, id)
                        .orElseThrow(UserActivismFondNotFoundException::new);
    }

    public List<ActivismUser> getFirst10UserByActivismMaxCountOurs(int actId){

        var a = activismRepo.findById(actId)
                        .orElseThrow(ActivismUserNotFoundException::new);

        return uActivismFondRepo.findFirst10ByActivismOrderByActivismCountOurs(a);

    }

    @Transactional
    public int save(UserActivismFond uFond){
        return uActivismFondRepo.save(uFond).getId();
    }


    public double getByUserSumCharity(int actUserId){

        var u = aUserRepo.findById(actUserId)
                        .orElseThrow(ActivismUserNotFoundException::new);

        return uActivismFondRepo.sumChartity(u);
    }
    
    @Transactional
    public int updateStatusDone(int actUserId, int id){
        var u = aUserRepo.findById(actUserId)
                        .orElseThrow(ActivismUserNotFoundException::new);
        uActivismFondRepo.changeStatusDone(id, u);
        return id;
    }

    @Transactional
    public int updateStatusPaid(int actUserId, int id){
        var u = aUserRepo.findById(actUserId)
                        .orElseThrow(ActivismUserNotFoundException::new);
        var v= uActivismFondRepo.findById(id)
                        .orElseThrow(UserActivismFondNotFoundException::new);
        aService.act(v);
        uActivismFondRepo.changeStatusPaid(id, u);
        return id;
    }


    public List<DateAndSumHours> statisticUserActivismIntervalDate(int actUserId, Date date1, Date date2){
        var u = aUserRepo.findById(actUserId)
                        .orElseThrow(ActivismUserNotFoundException::new);

        return uActivismFondRepo.statisticUserActivismIntervalDate(u, date1, date2);
    }


}
