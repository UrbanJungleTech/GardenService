package frentz.daniel.plants.service;

import frentz.daniel.garden.model.*;
import frentz.daniel.hardwareservice.client.model.HardwareController;
import frentz.daniel.hardwareservice.client.model.HardwareState;
import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.garden.model.*;
import frentz.daniel.hardwareservice.client.model.SensorReading;
import frentz.daniel.plants.converter.GardenConverter;
import frentz.daniel.plants.converter.GardenHardwareControllerConverter;
import frentz.daniel.plants.converter.PlantConverter;
import frentz.daniel.plants.converter.SensorConverter;
import frentz.daniel.plants.entity.GardenEntity;
import frentz.daniel.plants.entity.PlantEntity;
import frentz.daniel.plants.exception.NotFoundException;
import frentz.daniel.plants.repository.GardenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service()
public class GardenServiceImpl implements GardenService {

    private GardenRepository gardenRepository;
    private GardenConverter gardenConverter;
    private PlantService plantService;
    private GardenHardwareControllerConverter gardenHardwareControllerConverter;
    private HardwareControllerService hardwareControllerService;
    private HardwareService hardwareService;
    private SensorService sensorService;

    private PlantConverter plantConverter;
    private SensorConverter sensorConverter;
    public GardenServiceImpl(GardenRepository gardenRepository,
                             GardenConverter gardenConverter,
                             PlantService plantService,
                             HardwareControllerService hardwareControllerService,
                             GardenHardwareControllerConverter gardenHardwareControllerConverter,
                             HardwareService hardwareService,
                             SensorService sensorService,
                             PlantConverter plantConverter,
                             SensorConverter sensorConverter){
        this.gardenRepository = gardenRepository;
        this.gardenConverter = gardenConverter;
        this.plantService = plantService;
        this.gardenHardwareControllerConverter = gardenHardwareControllerConverter;
        this.hardwareControllerService = hardwareControllerService;
        this.hardwareService = hardwareService;
        this.sensorService = sensorService;
        this.plantConverter = plantConverter;
        this.sensorConverter = sensorConverter;
    }

    @Override
    public Garden create(Garden garden) {
        GardenEntity gardenEntity = new GardenEntity();
        this.gardenConverter.fillEntity(gardenEntity, garden);
        for(Plant plant : garden.getPlants()){
            PlantEntity plantEntity = this.plantService.create(plant);
            gardenEntity.getPlants().add(plantEntity);
            plantEntity.setGarden(gardenEntity);
            this.plantService.save(plantEntity);
        }
        if(garden.getHardwareController() != null) {
            HardwareController hardwareController = this.hardwareControllerService.createHardwareController(garden.getHardwareController());
            gardenEntity.setControllerId(hardwareController.getId());
        }
        gardenEntity = this.gardenRepository.save(gardenEntity);
        Garden result = this.gardenConverter.toModel(gardenEntity, true);
        return result;
    }

    @Override
    public Garden getGarden(long gardenId) {
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        return this.gardenConverter.toModel(gardenEntity, true);
    }

    @Override
    public List<Garden> getGardens() {
        List<GardenEntity> gardenEntities = this.gardenRepository.findAll();
        return this.gardenConverter.toModels(gardenEntities);
    }

    @Override
    public Plant addPlant(long gardenId, Plant plant) {
        PlantEntity plantEntity = this.plantService.create(plant);
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        plantEntity.setGarden(gardenEntity);
        this.plantService.save(plantEntity);
        return this.plantConverter.toModel(plantEntity);
    }

    @Override
    @Transactional
    public Garden removePlant(long gardenId, long plantId) {
        this.plantService.deletePlant(plantId);
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        gardenEntity.getPlants().removeIf((plant) -> {
            return plant.getId() == plantId;
        });
        this.gardenRepository.save(gardenEntity);
        return this.gardenConverter.toModel(gardenEntity, true);
    }

    @Override
    public void deleteGarden(long gardenId) {
        this.gardenRepository.deleteById(gardenId);
    }

    @Override
    public Garden addLight(long gardenId, Light light) {
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        this.hardwareControllerService.createLight(gardenEntity.getControllerId(), light);
        gardenEntity = this.gardenRepository.getOne(gardenId);
        return this.gardenConverter.toModel(gardenEntity, true);
    }

    @Override
    public Garden addWater(long gardenId, Water water) {
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        this.hardwareControllerService.createWater(gardenEntity.getControllerId(), water);
        gardenEntity = this.gardenRepository.getOne(gardenId);
        return this.gardenConverter.toModel(gardenEntity, true);
    }

    @Override
    public Garden addHeater(long gardenId, Heater heater) {
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        this.hardwareControllerService.createHeater(gardenEntity.getControllerId(), heater);
        gardenEntity = this.gardenRepository.getOne(gardenId);
        return this.gardenConverter.toModel(gardenEntity, true);
    }

    @Override
    public Garden setHardwareState(long gardenId, long hardwareId, HardwareState hardwareState) {
        this.hardwareService.setHardwareState(hardwareId, hardwareState);
        return this.getGarden(gardenId);
    }

    @Override
    public SensorReading readAverageSensor(long gardenId, String sensorType) {
        Garden garden = this.getGarden(gardenId);
        return this.sensorService.readAverageSensor(garden.getHardwareController().getId(), sensorType);
    }

    @Override
    public GardenSensor addSensor(long gardenId, GardenSensor gardenSensor) {
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        Sensor sensor = this.sensorConverter.toSensor(gardenSensor);
        sensor = this.hardwareControllerService.createSensor(gardenEntity.getControllerId(), sensor);
        return this.sensorConverter.toGardenSensor(sensor);
    }
}
