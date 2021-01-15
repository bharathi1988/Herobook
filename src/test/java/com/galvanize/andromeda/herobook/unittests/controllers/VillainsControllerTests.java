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
        createJoker();

        catWoman = CreateVillain("Cat Woman");
        villains = new ArrayList<>();
        villains.add(joker);
        villains.add(catWoman);
    }

    private void createJoker() {
        joker = CreateVillain("Joker");
        joker.setImage("URL");
        joker.setArchRival("Batman");
        joker.setRealName("Clark Kent");
        joker.setHeight(75);
        joker.setWeight(225);
        joker.setSpecialPower("Powers of flight, superhuman strength, x-ray vision, heat vision, cold breath, super-speed, enhanced hearing, and nigh-invulnerability.");
        joker.setIntelligence("Genius");
        joker.setStrength("2 Billion Tons");
        joker.setPower("Powers of flight, superhuman strength, x-ray vision, heat vision, cold breath, super-speed, enhanced hearing, and nigh-invulnerability.");
        joker.setSpeed("2000 Miles/sec");
        joker.setAgility("Superhuman Equilibrium");
        joker.setStory("Superman was born on the planet Krypton and was given the name Kal-El at birth. As a baby, his parents sent him to Earth in a small spaceship moments before Krypton was destroyed in a natural cataclysm. His ship landed in the American countryside, near the fictional town of Smallville.");
        joker.setDescription("Superman's powers include incredible strength, the ability to fly. X-ray vision, super speed, invulnerability to most attacks, super hearing, and super breath. He is nearly unstoppable. However, Superman does have one weakness, Kryptonite.");
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
                .andExpect(jsonPath("$.villainName").value("Joker"))
                .andExpect(jsonPath("$.image").value(joker.getImage()))
                .andExpect(jsonPath("$.realName").value(joker.getRealName()))
                .andExpect(jsonPath("$.height").value(joker.getHeight()))
                .andExpect(jsonPath("$.weight").value(joker.getWeight()))
                .andExpect(jsonPath("$.specialPower").value(joker.getSpecialPower()))
                .andExpect(jsonPath("$.intelligence").value(joker.getIntelligence()))
                .andExpect(jsonPath("$.strength").value(joker.getStrength()))
                .andExpect(jsonPath("$.power").value(joker.getPower()))
                .andExpect(jsonPath("$.speed").value(joker.getSpeed()))
                .andExpect(jsonPath("$.agility").value(joker.getAgility()))
                .andExpect(jsonPath("$.description").value(joker.getDescription()))
                .andExpect(jsonPath("$.story").value(joker.getStory()))
                .andExpect(jsonPath("$.archRival").value(joker.getArchRival()))
        ;

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
