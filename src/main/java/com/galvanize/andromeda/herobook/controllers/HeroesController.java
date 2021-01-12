package com.galvanize.andromeda.herobook.controllers;

import com.galvanize.andromeda.herobook.models.Hero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeroesController {

    @GetMapping("/herobooks/heroes")
    public List<Hero> getAllHeroes() {
        return new ArrayList<>();
    }

}
