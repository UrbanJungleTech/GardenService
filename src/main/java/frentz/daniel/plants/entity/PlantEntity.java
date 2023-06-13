package frentz.daniel.plants.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="plant")
public class PlantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String species;
    private LocalDateTime created;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "garden_id")
    private GardenEntity garden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public GardenEntity getGarden() {
        return garden;
    }

    public void setGarden(GardenEntity garden) {
        this.garden = garden;
    }
}
