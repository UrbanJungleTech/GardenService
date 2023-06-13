package frentz.daniel.plants.service;

import frentz.daniel.garden.model.*;
import frentz.daniel.hardwareservice.client.model.*;
import frentz.daniel.garden.model.*;
import frentz.daniel.plants.converter.*;
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
    private GardenHardwareConverter gardenHardwareConverter;

    public GardenServiceImpl(GardenRepository gardenRepository,
                             GardenConverter gardenConverter,
                             PlantService plantService,
                             HardwareControllerService hardwareControllerService,
                             GardenHardwareControllerConverter gardenHardwareControllerConverter,
                             HardwareService hardwareService,
                             SensorService sensorService,
                             PlantConverter plantConverter,
                             SensorConverter sensorConverter,
                             GardenHardwareConverter gardenHardwareConverter){
        this.gardenRepository = gardenRepository;
        this.gardenConverter = gardenConverter;
        this.plantService = plantService;
        this.gardenHardwareControllerConverter = gardenHardwareControllerConverter;
        this.hardwareControllerService = hardwareControllerService;
        this.hardwareService = hardwareService;
        this.sensorService = sensorService;
        this.plantConverter = plantConverter;
        this.sensorConverter = sensorConverter;
        this.gardenHardwareConverter = gardenHardwareConverter;
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
    public GardenHardware addHardware(long gardenId, GardenHardware gardenHardware) {
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseThrow(() -> new NotFoundException(Garden.class, gardenId));
        Hardware hardware = this.hardwareControllerService.createHardware(gardenEntity.getControllerId(), gardenHardware);
        return this.gardenHardwareConverter.toModel(hardware);
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
