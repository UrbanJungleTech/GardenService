package frentz.daniel.plants.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public class Plant {
    private long id;
    @NotNull
    private String name;
    private String species;
    @Null
    private LocalDateTime created;
    private Long gardenId;

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

    public Long getGardenId() {
        return gardenId;
    }

    public void setGardenId(Long gardenId) {
        this.gardenId = gardenId;
    }
}
