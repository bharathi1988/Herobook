package com.galvanize.andromeda.herobook.unittests.controllers;

import com.galvanize.andromeda.herobook.models.Villain;
import com.galvanize.andromeda.herobook.services.HeroesService;
import com.galvanize.andromeda.herobook.services.VillainsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
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

    private List<Villain> villains;
    private Villain joker;
    private Villain catWoman;

    @BeforeEach
    public void setUp() {
        joker = CreateVillain("Joker");
        catWoman = CreateVillain("Cat Woman");
        villains = new ArrayList<>();
        villains.add(joker);
        villains.add(catWoman);
    }

    private Villain CreateVillain(String villainName) {
        Villain villain = new Villain();
        villain.setVillainName(villainName);
        return villain;
    }

    @Test
    public void getAllVillainsTest() throws Exception {

        when(mockVillainsService.findAll()).thenReturn(villains);

        mockMvc.perform(get("/herobooks/villains"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].villainName").value("Joker"))
                .andExpect(jsonPath("$.[1].villainName").value("Cat Woman"));
    }

    @Test
    public void getVillainByName() throws Exception {

        Optional<Villain> optionalVillain = Optional.of(joker);
        when(mockVillainsService.findByVillainName("Joker")).thenReturn(optionalVillain);

        mockMvc.perform(get("/herobooks/villains/Joker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.villainName").value("Joker"));

        verify(mockVillainsService).findByVillainName("Joker");
    }

    @Test
    public void getVillainByIncorrectName() throws Exception {

        Optional<Villain> optionalVillain = Optional.ofNullable(null);
        when(mockVillainsService.findByVillainName("Deathstroke")).thenReturn(optionalVillain);

        mockMvc.perform(get("/herobooks/villains/Deathstroke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.status").value("Villain does not exist"));

    }

}
