package com.galvanize.andromeda.herobook.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Hero {

    @Column(name="HeroName")
    private String heroName;

    @Id
    private String id;

    @PrePersist
    private void ensureId(){
        this.setId(UUID.randomUUID().toString());
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
