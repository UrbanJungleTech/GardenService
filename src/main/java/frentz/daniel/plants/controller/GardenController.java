package frentz.daniel.plants.controller;

import frentz.daniel.plants.model.Garden;
import frentz.daniel.plants.model.Plant;
import frentz.daniel.plants.service.GardenService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/garden")
public class GardenController {

    private GardenService gardenService;

    GardenController(GardenService gardenService){
        this.gardenService = gardenService;
    }

    @PostMapping("/")
    public Garden create( @RequestBody @Valid Garden garden){
        return this.gardenService.createAndSaveGarden(garden);
    }

    @PostMapping("/{gardenId}/plants/")
    public Garden addPlant(@RequestBody Plant plant, @PathVariable long gardenId){
        this.gardenService.addPlant(gardenId, plant);
        return this.gardenService.getGarden(gardenId);
    }


    @DeleteMapping("/{gardenId}/plants/{plantId}")
    public Garden removePlant(@PathVariable long gardenId, @PathVariable long plantId){
        return this.gardenService.removePlant(gardenId, plantId);
    }

    @GetMapping("/")
    public List<Garden> getGardens(){
        return this.gardenService.getGardens();
    }

    @GetMapping("/{gardenId}")
    public Garden getGarden(@PathVariable long gardenId){
        return this.gardenService.getGarden(gardenId);
    }

    @DeleteMapping("/{gardenId}")
    public void deleteGarden(@PathVariable long gardenId){
        this.gardenService.deleteGarden(gardenId);
    }
}
