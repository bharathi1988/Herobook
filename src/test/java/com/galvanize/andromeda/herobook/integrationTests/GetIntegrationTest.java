package com.galvanize.andromeda.herobook.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.andromeda.herobook.models.Hero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class GetIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

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

}
