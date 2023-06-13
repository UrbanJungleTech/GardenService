package frentz.daniel.plants.controller;

import frentz.daniel.hardwareservice.client.model.SensorReading;
import frentz.daniel.plants.service.GardenService;
import frentz.daniel.plants.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorEndpoint {

    private SensorService sensorService;

    public SensorEndpoint(SensorService sensorService){
        this.sensorService = sensorService;
    }

    @GetMapping("/{sensorId}/reading")
    public ResponseEntity<SensorReading> readSensor(@PathVariable("sensorId") long sensorId){
        SensorReading result = this.sensorService.readSensor(sensorId);
        return ResponseEntity.ok(result);
    }
}
