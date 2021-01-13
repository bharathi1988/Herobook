package com.galvanize.andromeda.herobook.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.services.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HeroesController {

    @Autowired
    HeroesService heroesService;

    @GetMapping("/herobooks/heroes")
    public List<Hero> getAllHeroes() {
        return heroesService.findAll();
    }

    @GetMapping("/herobooks/heroes/{heroName}")
    public ResponseEntity<Object> getHeroByName(@PathVariable String heroName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // create a JSON object
        ObjectNode incorrectHero = mapper.createObjectNode();
        incorrectHero.put("status", "Hero does not exist");

        String incorrectHeroJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(incorrectHero);

        Optional<Hero> heroOptional = heroesService.findHeroByName(heroName);
        if(heroOptional.isPresent())
            return new ResponseEntity<>(heroOptional.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(incorrectHeroJson, HttpStatus.OK);
    }

    @PostMapping("/herobooks/heroes")
    @ResponseStatus(HttpStatus.CREATED)
    public Hero addSuperHero(@RequestBody Hero hero){
        return heroesService.save(hero);
    }

}
