package com.galvanize.andromeda.herobook.repository;

import com.galvanize.andromeda.herobook.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroesRepository extends JpaRepository<Hero, String> {
    Optional<Hero> findByHeroName(String heroName);
}
