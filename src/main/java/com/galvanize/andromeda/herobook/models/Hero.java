package com.galvanize.andromeda.herobook.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
public class Hero {

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
