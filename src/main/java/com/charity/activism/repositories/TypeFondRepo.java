package com.charity.activism.repositories;

import com.charity.activism.models.TypeFond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFondRepo extends JpaRepository<TypeFond,Integer> {
}
