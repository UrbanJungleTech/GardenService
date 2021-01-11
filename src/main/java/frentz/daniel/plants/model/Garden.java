package frentz.daniel.plants.model;

import frentz.daniel.controllerclient.model.HardwareController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Garden {
    private Long id;
    @NotNull
    private String name;
    private String description;
    private List<Plant> plants;
    private HardwareController hardwareController;

    public Garden(){
        this.plants = new ArrayList<>();
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

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HardwareController getHardwareController() {
        return hardwareController;
    }

    public void setHardwareController(HardwareController hardwareController) {
        this.hardwareController = hardwareController;
    }
}
