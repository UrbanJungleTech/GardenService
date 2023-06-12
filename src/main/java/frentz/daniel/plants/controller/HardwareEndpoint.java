package frentz.daniel.plants.controller;

import frentz.daniel.hardwareservice.client.model.HardwareState;
import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.Heater;
import frentz.daniel.garden.model.Light;
import frentz.daniel.garden.model.Water;
import frentz.daniel.plants.service.GardenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/garden/{gardenId}/hardware")
public class HardwareEndpoint {
    private GardenService gardenService;

    public HardwareEndpoint(GardenService gardenService){
        this.gardenService = gardenService;
    }

    @PostMapping("/light")
    public ResponseEntity<Garden> addLight(@PathVariable("gardenId") long gardenId, @RequestBody Light light){
        Garden result = this.gardenService.addLight(gardenId, light);
        return ResponseEntity.created(null).body(result);
    }

    @PostMapping("/water")
    public ResponseEntity<Garden> addWater(@PathVariable("gardenId") long gardenId, @RequestBody Water water){
        Garden result = this.gardenService.addWater(gardenId, water);
        return ResponseEntity.created(null).body(result);
    }

    @PostMapping("/heater")
    public ResponseEntity<Garden> addHeater(@PathVariable("gardenId") long gardenId, @RequestBody Heater heater){
        Garden result = this.gardenService.addHeater(gardenId, heater);
        return ResponseEntity.created(null).body(result);
    }

    @PutMapping("/{hardwareId}/state")
    public ResponseEntity<Garden> setHardwareState(@PathVariable("gardenId") long gardenId,
                                                   @PathVariable("hardwareId") long hardwareId,
                                                   @RequestBody HardwareState hardwareState){
        Garden result = this.gardenService.setHardwareState(gardenId, hardwareId, hardwareState);
        return ResponseEntity.ok(result);
    }
}
