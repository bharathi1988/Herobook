package com.galvanize.andromeda.herobook.services;

import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.repository.HeroesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroesService {

    //@Autowired
    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public List<Hero> findAll() {
        return heroesRepository.findAll();
    }
}
