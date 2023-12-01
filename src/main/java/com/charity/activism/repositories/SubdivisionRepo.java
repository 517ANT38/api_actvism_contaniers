package com.charity.activism.repositories;

import com.charity.activism.models.Subdivision;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubdivisionRepo extends JpaRepository<Subdivision,Integer> {

    Optional<Subdivision> findByName(String name);
}
