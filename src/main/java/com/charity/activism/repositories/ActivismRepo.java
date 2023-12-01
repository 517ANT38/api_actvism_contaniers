package com.charity.activism.repositories;

import com.charity.activism.models.Activism;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivismRepo extends JpaRepository<Activism,Integer> {


    Optional<Activism> findByName(String name);

}
