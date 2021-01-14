package com.galvanize.andromeda.herobook.unittests.controllers;

import com.galvanize.andromeda.herobook.services.HeroesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class VillainsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HeroesService mockHeroesService;

    @Test
    public void getAllVillainsTest() throws Exception {
        mockMvc.perform(get("/herobooks/villains"))
        .andExpect(status().isOk());
    }

}
