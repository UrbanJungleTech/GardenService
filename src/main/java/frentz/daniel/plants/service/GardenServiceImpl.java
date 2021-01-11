package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.HardwareController;
import frentz.daniel.plants.converter.GardenConverter;
import frentz.daniel.plants.dao.GardenRepository;
import frentz.daniel.plants.dao.PlantRepository;
import frentz.daniel.plants.entity.GardenEntity;
import frentz.daniel.plants.entity.PlantEntity;
import frentz.daniel.plants.exception.NotFoundException;
import frentz.daniel.plants.model.Garden;
import frentz.daniel.plants.model.Plant;
import org.springframework.stereotype.Service;
import service.HardwareClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service()
public class GardenServiceImpl implements GardenService {

    private GardenRepository gardenRepository;
    private GardenConverter gardenConverter;
    private PlantService plantService;
    private PlantRepository plantRepository;
    private HardwareClient hardwareClient;

    public GardenServiceImpl(GardenRepository gardenRepository,
                             GardenConverter gardenConverter,
                             PlantService plantService,
                             PlantRepository plantRepository,
                             HardwareClient hardwareClient){
        this.gardenRepository = gardenRepository;
        this.gardenConverter = gardenConverter;
        this.plantService = plantService;
        this.plantRepository = plantRepository;
        this.hardwareClient = hardwareClient;
    }

    @Override
    public void createGarden(Garden garden) {
        for(Plant plant : garden.getPlants()){
            this.plantService.createPlant(plant);
        }
    }

    @Override
    public Garden createAndSaveGarden(Garden garden) {
        GardenEntity gardenEntity = new GardenEntity();
        gardenEntity.setName(garden.getName());
        gardenEntity.setDescription(garden.getDescription());
        for(Plant plant : garden.getPlants()){
            PlantEntity plantEntity = this.plantService.createAndSavePlant(plant);
            gardenEntity.getPlants().add(plantEntity);
            plantEntity.setGarden(gardenEntity);
        }
        garden.setHardwareController(this.hardwareClient.createHardwareController(garden.getHardwareController()));
        gardenEntity.setControllerId(garden.getHardwareController().getId());
        gardenEntity = this.gardenRepository.save(gardenEntity);
        Garden result = this.gardenConverter.toModel(gardenEntity, true);


        return result;
    }

    @Override
    public Garden getGarden(long id) {
        Optional<GardenEntity> gardenEntity = this.gardenRepository.findById(id);
        if(!gardenEntity.isPresent()){
            throw new NotFoundException(Garden.class, id);
        }
        return this.gardenConverter.toModel(gardenEntity.get(), true);
    }

    @Override
    public List<Garden> getGardens() {
        List<GardenEntity> gardenEntities = this.gardenRepository.findAll();
        return this.gardenConverter.toModels(gardenEntities);
    }

    @Override
    public void addPlant(long gardenId, Plant plant) {
        PlantEntity plantEntity = this.plantService.createAndSavePlant(plant);
        GardenEntity gardenEntity = this.gardenRepository.getOne(gardenId);
        plantEntity.setGarden(gardenEntity);
        this.plantRepository.save(plantEntity);
    }

    @Override
    @Transactional
    public Garden removePlant(long gardenId, long plantId) {
        PlantEntity plantEntity = this.plantRepository.getOne(plantId);
        if(plantEntity.getGarden().getId() != gardenId){
            throw new NotFoundException(Plant.class, plantId);
        }
        plantRepository.deleteById(plantId);
        GardenEntity gardenEntity = this.gardenRepository.findById(gardenId).orElseGet(null);
        gardenEntity.getPlants().removeIf((PlantEntity plant) -> {
            return plant.getId() == plantId;
        });
        this.gardenRepository.save(gardenEntity);
        return gardenConverter.toModel(gardenEntity, true);
    }

    @Override
    public void deleteGarden(long gardenId) {
        this.gardenRepository.deleteById(gardenId);
    }
}
