package com.galvanize.andromeda.herobook.integrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.andromeda.herobook.models.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GetIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    Hero superMan;

    @BeforeEach
    public void setup(){
        superMan = new Hero();
        superMan.setImage("URL");
        superMan.setRealName("Clark Kent");
        superMan.setHeroName("Super Man");
        superMan.setHeight(75);
        superMan.setWeight(225);
        superMan.setSpecialPower("Powers of flight, superhuman strength, x-ray vision, heat vision, cold breath, super-speed, enhanced hearing, and nigh-invulnerability.");
        superMan.setIntelligence("Genius");
        superMan.setStrength("2 Billion Tons");
        superMan.setPower("Powers of flight, superhuman strength, x-ray vision, heat vision, cold breath, super-speed, enhanced hearing, and nigh-invulnerability.");
        superMan.setSpeed("2000 Miles/sec");
        superMan.setAgility("Superhuman Equilibrium");
        superMan.setStory("Superman was born on the planet Krypton and was given the name Kal-El at birth. As a baby, his parents sent him to Earth in a small spaceship moments before Krypton was destroyed in a natural cataclysm. His ship landed in the American countryside, near the fictional town of Smallville.");
        superMan.setDescription("Superman's powers include incredible strength, the ability to fly. X-ray vision, super speed, invulnerability to most attacks, super hearing, and super breath. He is nearly unstoppable. However, Superman does have one weakness, Kryptonite.");
    }

    @Test
    public void getHeroes() throws Exception {
        Hero superMan = new Hero();
        superMan.setHeroName("Super Man");

        Hero spiderMan = new Hero();
        spiderMan.setHeroName("Spider Man");

        mockMvc.perform(post("/herobooks/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(superMan))
               )
            .andExpect(status().isCreated());

        mockMvc.perform(post("/herobooks/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(spiderMan))
        )
                .andExpect(status().isCreated());

        mockMvc.perform(get("/herobooks/heroes"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].heroName").value(superMan.getHeroName()))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].heroName").value(spiderMan.getHeroName()))
                ;
    }

    @Test
    public void getHeroByNameTest() throws Exception {

        mockMvc.perform(post("/herobooks/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(superMan))
        )
                .andExpect(status().isCreated());

        mockMvc.perform(get("/herobooks/heroes/Super Man"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.heroName").value(superMan.getHeroName()))
                .andExpect(jsonPath("$.image").value(superMan.getImage()))
                .andExpect(jsonPath("$.realName").value(superMan.getRealName()))
                .andExpect(jsonPath("$.height").value(superMan.getHeight()))
                .andExpect(jsonPath("$.weight").value(superMan.getWeight()))
                .andExpect(jsonPath("$.specialPower").value(superMan.getSpecialPower()))
                .andExpect(jsonPath("$.intelligence").value(superMan.getIntelligence()))
                .andExpect(jsonPath("$.strength").value(superMan.getStrength()))
                .andExpect(jsonPath("$.power").value(superMan.getPower()))
                .andExpect(jsonPath("$.speed").value(superMan.getSpeed()))
                .andExpect(jsonPath("$.agility").value(superMan.getAgility()))
                .andExpect(jsonPath("$.description").value(superMan.getDescription()))
                .andExpect(jsonPath("$.story").value(superMan.getStory()))
        ;

    }

    @Test
    public void getVillains() throws Exception {
        mockMvc.perform(get("/herobooks/villains"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.length()").value(1))

        ;
    }

}
