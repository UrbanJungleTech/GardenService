package frentz.daniel.plants.controller;

import frentz.daniel.plants.model.Plant;
import frentz.daniel.plants.service.PlantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    private PlantService plantService;

    public PlantController(PlantService plantService){
        this.plantService = plantService;
    }

    @GetMapping("/{id}")
    public Plant getPlant(@PathVariable long id){
        return this.plantService.getPlant(id);
    }

    @GetMapping("/")
    public List<Plant> getPlants(){
        return this.plantService.getPlants();
    }

    @PostMapping("/")
    public Plant create(@RequestBody Plant plant){
        this.plantService.createAndSavePlant(plant);
        return plant;
    }

    @DeleteMapping("/{plantId}")
    public void delete(@PathVariable("plantId") long plantId){
        this.plantService.deletePlant(plantId);
    }
}
