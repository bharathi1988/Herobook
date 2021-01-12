package com.galvanize.andromeda.herobook.unittests.services;


import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.services.HeroesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeroesServiceTests {

    private HeroesService heroesService;

    @BeforeEach
    public void setup(){
        heroesService = new HeroesService();
    }

    @Test
    public void findAllTest(){
        List<Hero> heroList = heroesService.findAll();
        assertTrue(heroList.isEmpty());
    }

}
