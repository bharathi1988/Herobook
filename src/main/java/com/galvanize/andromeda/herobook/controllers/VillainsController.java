package com.galvanize.andromeda.herobook.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.galvanize.andromeda.herobook.models.Villain;
import com.galvanize.andromeda.herobook.services.VillainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VillainsController {

    @Autowired
    private VillainsService villainsService;

    @GetMapping("/herobooks/villains")
    public List<Villain> getAllVillains() {
        return villainsService.findAll();
    }

    @GetMapping("/herobooks/villains/{villainName}")
    public ResponseEntity<Object> getVillainByName(@PathVariable String villainName) throws JsonProcessingException {

        Optional<Villain> optionalVillain = villainsService.findByVillainName(villainName);

        if (optionalVillain.isPresent()) {
            return new ResponseEntity<>(optionalVillain.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CreateIncorrectVillainJsonMessage(), HttpStatus.OK);
        }
    }

    private String CreateIncorrectVillainJsonMessage() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode incorrectVillain = mapper.createObjectNode();
        incorrectVillain.put("status", "Villain does not exist");
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(incorrectVillain);
    }
}
