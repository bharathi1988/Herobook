package com.galvanize.andromeda.herobook.unittests.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class HeroesControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void GetHeroesTest() throws Exception {
        mockMvc.perform(get("/herobooks/heroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

}
