package frentz.daniel.plants.controller;

import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.controllerclient.model.SensorType;
import frentz.daniel.model.Garden;
import frentz.daniel.model.Plant;
import frentz.daniel.plants.service.GardenAdditionService;
import frentz.daniel.plants.service.GardenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.List;

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
    public Garden create( @RequestBody @Valid Garden garden){
        return this.gardenAdditionService.addGarden(garden);
    }

    @PostMapping("/{gardenId}/plants/")
    public ResponseEntity<Garden> addPlant(@RequestBody Plant plant, @PathVariable long gardenId){
        this.gardenAdditionService.addPlant(gardenId, plant);
        return ResponseEntity.created(null).body(this.gardenService.getGarden(gardenId));
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
    public ResponseEntity deleteGarden(@PathVariable long gardenId){
        this.gardenAdditionService.deleteGarden(gardenId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/realtime")
    public SseEmitter getGardensRealtime(){
        SseEmitter result = new SseEmitter();
        this.gardenAdditionService.addSubscriber(result);
        return result;
    }

    @PostMapping("/{gardenId}/hardwareController/sensor")
    public ResponseEntity<Garden> addSensor(@PathVariable long gardenId, @RequestBody Sensor sensor){
        Garden result = this.gardenService.addSensor(gardenId, sensor);
        return ResponseEntity.created(null).body(result);
    }

    @GetMapping("/{gardenId}/averagesensor/{sensorType}")
    public ResponseEntity<Double> readAverageSensor(@PathVariable("gardenId") long gardenId, @PathVariable("sensorType") SensorType sensorType){
        double result = this.gardenService.readAverageSensor(gardenId, sensorType);
        return ResponseEntity.ok(result);
    }
}
