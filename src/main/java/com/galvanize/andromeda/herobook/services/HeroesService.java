package com.galvanize.andromeda.herobook.services;

import com.galvanize.andromeda.herobook.models.Hero;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeroesService {
    public List<Hero> findAll() {
        return new ArrayList<>();
    }
}
