package frentz.daniel.plants.controller;

import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.controllerclient.model.SensorType;
import frentz.daniel.model.Garden;
import frentz.daniel.plants.service.GardenService;
import frentz.daniel.plants.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorEndpoint {

    private SensorService sensorService;
    private GardenService gardenService;

    public SensorEndpoint(SensorService sensorService, GardenService gardenService){
        this.sensorService = sensorService;
        this.gardenService = gardenService;
    }

    @GetMapping("/{sensorId}/reading")
    public ResponseEntity<Double> readSensor(@PathVariable("sensorId") long sensorId){
        double result = this.sensorService.readSensor(sensorId);
        return ResponseEntity.ok(result);
    }
}
