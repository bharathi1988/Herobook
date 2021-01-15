package com.galvanize.andromeda.herobook.repository;

import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.models.Villain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VillainsRepository extends JpaRepository<Villain, String> {
    Optional<Villain> findByVillainName(String villainName);
}
