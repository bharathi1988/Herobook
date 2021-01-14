package com.galvanize.andromeda.herobook.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Hero {
    @Id
    private String id;

    @Column(name="HeroName")
    private String heroName;

    private String image;

    private String realName;

    private int height;

    private int weight;

    private String specialPower;

    private String intelligence;

    private String strength;

    private String power;

    private String speed;

    private String agility;

    private String description;

    @Lob
    @Column
    private String story;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(String specialPower) {
        this.specialPower = specialPower;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getAgility() {
        return agility;
    }

    public void setAgility(String agility) {
        this.agility = agility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
