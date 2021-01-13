package com.galvanize.andromeda.herobook.services;

import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.repository.HeroesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public List<Hero> findAll() {
        return heroesRepository.findAll();
    }

    public Hero save(Hero hero){
        return heroesRepository.save(hero);
    }

    public Optional<Hero> findHeroByName(String heroName) {
        return heroesRepository.findByHeroName(heroName);
    }
}
