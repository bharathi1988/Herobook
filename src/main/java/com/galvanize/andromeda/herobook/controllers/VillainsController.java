package com.galvanize.andromeda.herobook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VillainsController {

    @GetMapping("/herobooks/villains")
    public void getAllVillains() {

    }
}
