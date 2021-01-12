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
        List<Hero> heroes = new ArrayList<>();

        Hero superMan = new Hero();
        superMan.setHeroName("Super Man");
        heroes.add(superMan);

        Hero spiderMan = new Hero();
        spiderMan.setHeroName("Spider Man");
        heroes.add(spiderMan);
        when(mockHeroesService.findAll()).thenReturn(heroes);
        mockMvc.perform(get("/herobooks/heroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].heroName").value("Super Man"))
                .andExpect(jsonPath("$.[1].heroName").value("Spider Man"));

    }

}
