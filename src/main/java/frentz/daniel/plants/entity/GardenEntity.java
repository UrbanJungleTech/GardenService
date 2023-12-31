package frentz.daniel.plants.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="garden")
public class GardenEntity {

    public GardenEntity(){
        this.plants = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PlantEntity> plants;
    private long controllerId;


    public void addPlant(PlantEntity plantEntity){
        this.plants.add(plantEntity);
    }

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

    public List<PlantEntity> getPlants() {
        return plants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getControllerId() {
        return controllerId;
    }

    public void setControllerId(long controllerId) {
        this.controllerId = controllerId;
    }

    public void setPlants(List<PlantEntity> plants) {
        this.plants = plants;
    }
}
