package com.galvanize.andromeda.herobook.services;

import com.galvanize.andromeda.herobook.models.Villain;
import com.galvanize.andromeda.herobook.repository.VillainsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VillainsService {

    private VillainsRepository villainsRepository;

    public VillainsService(VillainsRepository villainsRepository) {
        this.villainsRepository = villainsRepository;
    }


    public List<Villain> findAll() {
        return villainsRepository.findAll();
    }

    public Optional<Villain> findByVillainName(String villainName) {
        return villainsRepository.findByVillainName(villainName);
    }
}
