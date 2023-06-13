package frentz.daniel.plants.controller;

import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.service.GardenService;
import frentz.daniel.plants.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/plants")
public class PlantEndpoint {
    private PlantService plantService;

    public PlantEndpoint(PlantService plantService){
        this.plantService = plantService;
    }

    @GetMapping("/{plantId}")
    public ResponseEntity<Plant> getPlant(@PathVariable long plantId){
        Plant result = this.plantService.getPlant(plantId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public ResponseEntity<List<Plant>> getPlants(){
        List<Plant> result = this.plantService.getPlants();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{plantId}")
    public ResponseEntity delete(@PathVariable("plantId") long plantId){
        this.plantService.deletePlant(plantId);
        return ResponseEntity.noContent().build();
    }

}
