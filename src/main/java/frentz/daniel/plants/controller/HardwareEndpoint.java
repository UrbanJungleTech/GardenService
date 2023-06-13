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
    public ResponseEntity<Light> addLight(@PathVariable("gardenId") long gardenId, @RequestBody Light light){
        Light result = (Light)this.gardenService.addHardware(gardenId, light);
        return ResponseEntity.created(null).body(result);
    }

    @PostMapping("/water")
    public ResponseEntity<Water> addWater(@PathVariable("gardenId") long gardenId, @RequestBody Water water){
        Water result = (Water)this.gardenService.addHardware(gardenId, water);
        return ResponseEntity.created(null).body(result);
    }

    @PostMapping("/heater")
    public ResponseEntity<Heater> addHeater(@PathVariable("gardenId") long gardenId, @RequestBody Heater heater){
        Heater result = (Heater)this.gardenService.addHardware(gardenId, heater);
        return ResponseEntity.created(null).body(result);
    }

    @PutMapping("/{hardwareId}/state")
    public ResponseEntity<HardwareState> setHardwareState(@PathVariable("gardenId") long gardenId,
                                                   @PathVariable("hardwareId") long hardwareId,
                                                   @RequestBody HardwareState hardwareState){
        HardwareState result = this.gardenService.setHardwareState(gardenId, hardwareId, hardwareState);
        return ResponseEntity.ok(result);
    }
}
