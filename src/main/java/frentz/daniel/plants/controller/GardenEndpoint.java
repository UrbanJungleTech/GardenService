package frentz.daniel.plants.controller;

import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.GardenSensor;
import frentz.daniel.garden.model.Plant;
import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.hardwareservice.client.model.SensorReading;
import frentz.daniel.plants.service.GardenAdditionService;
import frentz.daniel.plants.service.GardenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/garden")
public class GardenEndpoint {

    private GardenAdditionService gardenAdditionService;
    private GardenService gardenService;

    GardenEndpoint(GardenAdditionService gardenAdditionService,
                   GardenService gardenService){
        this.gardenAdditionService = gardenAdditionService;
        this.gardenService = gardenService;
    }

    @PostMapping("/")
    public ResponseEntity<Garden> create(@RequestBody Garden garden){
        Garden result = this.gardenAdditionService.addGarden(garden);
        return ResponseEntity.created(null).body(result);
    }

    @PostMapping("/{gardenId}/plants/")
    public ResponseEntity<Plant> addPlant(@RequestBody Plant plant, @PathVariable long gardenId){
        Plant result = this.gardenAdditionService.addPlant(gardenId, plant);
        return ResponseEntity.created(null).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<List<Garden>> getGardens(){
        List<Garden> result = this.gardenService.getGardens();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{gardenId}")
    public ResponseEntity<Garden> getGarden(@PathVariable long gardenId){
        Garden result = this.gardenService.getGarden(gardenId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{gardenId}")
    public ResponseEntity deleteGarden(@PathVariable long gardenId){
        this.gardenAdditionService.deleteGarden(gardenId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/realtime")
    public SseEmitter getGardensRealtime(){
        SseEmitter result = new SseEmitter();
        this.gardenAdditionService.addSubscriber(result);
        return result;
    }

    @PostMapping("/{gardenId}/hardwareController/sensor")
    public ResponseEntity<GardenSensor> addSensor(@PathVariable long gardenId, @RequestBody GardenSensor sensor){
        GardenSensor result = this.gardenService.addSensor(gardenId, sensor);
        return ResponseEntity.created(null).body(result);
    }

    @GetMapping("/{gardenId}/averagesensor/{sensorType}")
    public ResponseEntity<SensorReading> readAverageSensor(@PathVariable("gardenId") long gardenId, @PathVariable("sensorType")String sensorType){
        SensorReading result = this.gardenService.readAverageSensor(gardenId, sensorType);
        return ResponseEntity.ok(result);
    }
}
