package com.charity.activism.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.charity.activism.exceptions.ActivismUserNotFoundException;
import com.charity.activism.exceptions.RoleNotFoundException;
import com.charity.activism.exceptions.SubdivisionNotFoundException;
import com.charity.activism.exceptions.UserActivismFondNotFoundException;
import com.charity.activism.models.ActivismUser;
import com.charity.activism.models.Role;
import com.charity.activism.repositories.ActivismUserRepo;
import com.charity.activism.repositories.RoleRepository;
import com.charity.activism.repositories.SubdivisionRepo;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ActivismUserService {

    private final ActivismUserRepo activismUserRepo;
    private final SubdivisionRepo  subdivisionRepo;
    private final RoleRepository pRepository;
    private final PasswordEncoder passwordEncoder;
    

    @Transactional
    @PostConstruct
    void postConstruct(){
        if(!activismUserRepo.existsByLogin("admin")){
            var a = new ActivismUser();
            a.setLogin("admin");
            var r = new Role("ROLE_ADMIN");
            r.setActivismUsers(Set.of(a));
            a.setRoles(Set.of(r));
            a.setPassword(passwordEncoder.encode("admin"));
            activismUserRepo.save(a);
        }
        if(!activismUserRepo.existsByLogin("fisrtUser")){
            var a = new ActivismUser();
            a.setLogin("fisrtUser");
            var r = new Role("ROLE_USER");
            r.setActivismUsers(Set.of(a));
            a.setRoles(Set.of(r));
            a.setPassword(passwordEncoder.encode("fisrtUser"));
            activismUserRepo.save(a);
        }
    }


    public List<ActivismUser> getAll(){
        return activismUserRepo.findAll();
    }

    public List<ActivismUser> getAllBySubdivision(int subId){

        var s = subdivisionRepo.findById(subId)
                            .orElseThrow(SubdivisionNotFoundException::new);
    
        return activismUserRepo.findBySubdivision(s);
    
    }
 
    public List<ActivismUser> getFirst10ActivismUserMaxCountActivism(){
        return activismUserRepo.findFirst10OrderByCountUserActivismFondsAsc();
    }


    public ActivismUser getById(int id){

        return activismUserRepo.findById(id)
                            .orElseThrow(UserActivismFondNotFoundException::new);
    }

    @Transactional
    public int save(ActivismUser a){
        var r = pRepository.findByNameRole("ROLE_USER")
                                .get();
        a.setRoles(Set.of(r));
        var s = subdivisionRepo.findByName(a.getSubdivision().getName())
                                .orElse(a.getSubdivision());
        a.setSubdivision(s);
        var us1 = Optional.ofNullable(s.getActivismUsers());
        var l1 = us1.orElse(new ArrayList<>());
        l1.add(a);
        s.setActivismUsers(l1);
        a.setPassword(passwordEncoder.encode(a.getPassword()));
        return activismUserRepo.save(a).getId();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public int addRoleUser(int id, String nameRole){
        var r = pRepository.findByNameRole(nameRole)
            .orElseThrow(RoleNotFoundException::new);
        if(!activismUserRepo.existsById(id)){
            throw new ActivismUserNotFoundException();
        }
        activismUserRepo.addRoleUser(r.getId(), id);

        return id;
    }

    public ActivismUser getByLogin(String login){

        return activismUserRepo.findByLogin(login)
                            .orElseThrow(UserActivismFondNotFoundException::new);
    }

}
