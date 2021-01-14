package com.galvanize.andromeda.herobook.controllers;

import com.galvanize.andromeda.herobook.models.Villain;
import com.galvanize.andromeda.herobook.services.VillainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VillainsController {

    @Autowired
    private VillainsService villainsService;

    @GetMapping("/herobooks/villains")
    public List<Villain> getAllVillains() {
        return villainsService.findAll();
    }

    @GetMapping("/herobooks/villains/{villainName}")
    public Villain getVillainByName() {
        return villainsService.findByVillainName();
    }
}
