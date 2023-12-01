package com.charity.activism.repositories;

import com.charity.activism.models.Fond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FondRepo extends JpaRepository<Fond,Integer> {

    Optional<Fond> findByName(String name);

}
