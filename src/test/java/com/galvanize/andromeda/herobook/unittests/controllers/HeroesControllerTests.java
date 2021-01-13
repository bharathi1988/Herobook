package com.galvanize.andromeda.herobook.unittests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.services.HeroesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.configuration.GlobalConfiguration.validate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class HeroesControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    HeroesService mockHeroesService;

    private Hero superMan;
    @BeforeEach
    public void setup(){
        superMan = new Hero();
        superMan.setHeroName("Super Man");
    }

    @Test
    public void GetHeroesTest() throws Exception {
        List<Hero> heroes = new ArrayList<>();

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

    @Test
    public void addSuperHeroTest() throws Exception {
        Hero expectedSuperMan = new Hero();
        expectedSuperMan.setId("AAAA");
        expectedSuperMan.setHeroName("Super Man");

        when(mockHeroesService.save(any())).thenReturn(expectedSuperMan);

        mockMvc.perform(post("/herobooks/heroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(superMan)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.heroName").value(superMan.getHeroName()))
        ;

        verify(mockHeroesService).save(any());
    }

    @Test
    public void getHeroByNameTest() throws Exception {
        Hero expectedSuperMan = new Hero();
        expectedSuperMan.setHeroName("Super Man");

        Optional<Hero> optionalHero = Optional.of(expectedSuperMan);

        when(mockHeroesService.findHeroByName("Super Man")).
                thenReturn(optionalHero);

        mockMvc.perform(get("/herobooks/heroes/Super Man"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.heroName").value(superMan.getHeroName()));

        verify(mockHeroesService).findHeroByName("Super Man");
    }

}
