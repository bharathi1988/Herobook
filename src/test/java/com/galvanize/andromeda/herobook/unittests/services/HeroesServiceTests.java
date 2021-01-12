package com.galvanize.andromeda.herobook.unittests.services;


import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.repository.HeroesRepository;
import com.galvanize.andromeda.herobook.services.HeroesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HeroesServiceTests {

    private HeroesService heroesService;
    private HeroesRepository mockHeroesRepository;

    @BeforeEach
    public void setup(){
        mockHeroesRepository = mock(HeroesRepository.class);
        heroesService = new HeroesService(mockHeroesRepository);
    }

    @Test
    public void findAllTest(){
        List<Hero> expectedHeroList = new ArrayList<>();

        Hero superMan = new Hero();
        superMan.setHeroName("Super Man");
        expectedHeroList.add(superMan);

        when(mockHeroesRepository.findAll()).thenReturn(expectedHeroList);

        List<Hero> heroList = heroesService.findAll();
        assertEquals(expectedHeroList.size(), heroList.size());

        verify(mockHeroesRepository).findAll();

    }

}
