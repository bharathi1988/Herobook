# **Herobook API**
- - - - - - - - - - - - - - - - - - - - - - - -

GC publishing wants to develop something new for the fans. Your team is working on a new application for fans to browse all the heroes and villains from the comics.

Your task is to develop the API specs and implement them using Spring Web.
 
### Instructions ###
1. Create a new repository.

2. Share the link to your new repo with your group. One repo per group.

3. Submit your work below.

Use the acceptance criteria provided to build the technical spec for the heroes resource. Once completed, implement it. The spec should include a table of the URIs, methods and descriptions for each. It should also include examples of the expected requests and responses.

Hint: You do not have to follow the sequence of the A.C. as long as you accomplish them all at the end.

### **Personas**
GC publishing expects the following user personas:

|Name	|Role|
|-------|:---|
|Visitor|A regular visitor who comes to view the heroes.|

Note: roles are provided by the requester and trusted automatically by the server

### **Acceptance Criteria**
**Visitors**

As a visitor, I can view all the heroes.
````
When I view all the heros
Then I can see names of all heros
````
As a visitor, I can see information about any individual hero so that I can see their stats.

````
Rule: Heroes have an image, real name, hero name, height, weight, special power, intelligence, strength, power, speed, agility, description, and story.

Given I have the name of a hero
When I retreive the hero
Then I can view all the details for that hero

Given I have an incorrect hero name
When I retreive details for that hero
Then I receive a message that it doesn't exist
````

### **Technical Specification**

| URI | Method | Description |
|-----|--------|-------------|
|/herobooks/heroes|GET| We are going get all the Heroes|
|/herobooks/heroes/{hero_name}|GET| We are going to get the details of the requested hero|
|/herobooks/heroes|POST|To add a new hero|

### **Sample Request and Response**

GET /herobooks/heroes
````
[
    {
        "id" : "AAAA",
       "hero-name" : "Super Man" 
    },
    {
        "id": "AAAB",
        "hero-name": "Wonder Woman"
    }
]
````

GET /herobooks/heroes/Super Man
````
{
    "id" : "AAAA"
   "hero-name" : "Super Man" 
}
````

POST /herobooks/heroes
````
Request:
{
    "hero-name" : "Spider Man"
}
````

````
Response:
{
    "id" : "AAAC",
    "hero-name" : "Spider Man"
    ...
}
````

### **Villains**
GC publishing wants to add a feature for users to learn all about their favorite villains!

#### **Stories and Acceptance Criteria**
As a visitor, I can view all the villains.
````
When I view all the villains
Then I can see names of all villains
````

As a visitor, I can see information about any individual hero so that I can see their stats.

````
Rule: Villains have an arch rival, image, real name, hero name, height, weight, special power, intelligence, strength, power, speed, agility, description, and story.

Given I have the name of a villain
When I retreive the villain
Then I can view all the details for that villain

Given I have an incorrect villain name
When I retreive details for that villain
Then I receive a message that it doesn't exist
````


### **Technical Specification**

| URI | Method | Description |
|-----|--------|-------------|
|/herobooks/villains|GET |Get all the Names of the Villains. |
|/herobooks/villain/{villain-name}|GET|Get all the details for that Villain. |

### **Sample Request and Response**

GET /herobooks/villains
````
[
    {
        "id" : "BBBA",
       "hero-name" : "Joker" 
    },
    {
        "id": "BBBB",
        "hero-name": "Cat Woman"
    }
]
````

GET /herobooks/villain/Joker
````
{
   "id" : "BBBA",
   "hero-name" : "Joker" 
   ...
   
}
````
