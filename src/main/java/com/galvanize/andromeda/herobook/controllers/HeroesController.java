package com.galvanize.andromeda.herobook.controllers;

import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.services.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/herobooks/heroes/{heroName}")
    public Hero getHeroByName(@PathVariable String heroName) {
        return heroesService.findHeroByName(heroName).get();
    }

    @PostMapping("/herobooks/heroes")
    @ResponseStatus(HttpStatus.CREATED)
    public Hero addSuperHero(@RequestBody Hero hero){
        return heroesService.save(hero);
    }

}
