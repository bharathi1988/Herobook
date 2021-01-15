package com.galvanize.andromeda.herobook.unittests.services;

import com.galvanize.andromeda.herobook.models.Hero;
import com.galvanize.andromeda.herobook.models.Villain;
import com.galvanize.andromeda.herobook.repository.VillainsRepository;
import com.galvanize.andromeda.herobook.services.VillainsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VillainServicesTests {
    private VillainsService villainsService;
    private VillainsRepository mockVillainRepository;

    @BeforeEach
    public void setup(){
        mockVillainRepository = mock(VillainsRepository.class);
        villainsService = new VillainsService(mockVillainRepository);
    }

    @Test
    public void findAllTest(){
        List<Villain> villainList = new ArrayList<>();

        Villain villain = new Villain();
        villain.setVillainName("Joker");
        villainList.add(villain);

        when(mockVillainRepository.findAll()).thenReturn(villainList);

        List<Villain> actualVillainList = villainsService.findAll();
        assertEquals(villainList.size(), actualVillainList.size());

        verify(mockVillainRepository).findAll();
    }

    @Test
    public void findVillainByNameTest() {
        Villain joker = new Villain();
        joker.setVillainName("Joker");

        Villain expectedJoker = new Villain();
        expectedJoker.setVillainName("Joker");

        Optional<Villain> optionalVillain = Optional.of(expectedJoker);

        when(mockVillainRepository.findByVillainName("Joker")).thenReturn(optionalVillain);

        Optional<Villain> result = villainsService.findByVillainName("Joker");
        assertEquals(expectedJoker, result.get());

        verify(mockVillainRepository).findByVillainName("Joker");

    }


}
