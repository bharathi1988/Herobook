package com.galvanize.andromeda.herobook.unittests.controllers;

import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.services.HeroesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class HeroesControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HeroesService mockHeroesService;

    @Test
    public void GetHeroesTest() throws Exception {
        Hero hero = new Hero();
        hero.setHeroName("Super Man");
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        when(mockHeroesService.findAll()).thenReturn(heroes);
        mockMvc.perform(get("/herobooks/heroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].heroName").value("Super Man"));
    }

}
