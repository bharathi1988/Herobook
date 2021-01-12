package com.galvanize.andromeda.herobook.controllers;

import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.services.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeroesController {

    @Autowired
    HeroesService heroesService;

    @GetMapping("/herobooks/heroes")
    public List<Hero> getAllHeroes() {
        return heroesService.findAll();
    }

}
