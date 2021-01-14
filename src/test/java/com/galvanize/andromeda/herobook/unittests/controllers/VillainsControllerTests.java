package com.galvanize.andromeda.herobook.unittests.controllers;

import com.galvanize.andromeda.herobook.models.Villain;
import com.galvanize.andromeda.herobook.services.HeroesService;
import com.galvanize.andromeda.herobook.services.VillainsService;
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
public class VillainsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HeroesService mockHeroesService;

    @MockBean
    VillainsService mockVillainsService;

    @Test
    public void getAllVillainsTest() throws Exception {

        Villain joker = new Villain();
        joker.setVillainName("Joker");
        Villain catWoman = new Villain();
        catWoman.setVillainName("Cat Woman");
        List<Villain> villains = new ArrayList<>();
        villains.add(joker);
        villains.add(catWoman);

        when(mockVillainsService.findAll()).thenReturn(villains);

        mockMvc.perform(get("/herobooks/villains"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].villainName").value("Joker"))
                .andExpect(jsonPath("$.[1].villainName").value("Cat Woman"));
    }

}
