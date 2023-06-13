package frentz.daniel.plants.service;

import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.converter.PlantConverterImpl;
import frentz.daniel.plants.repository.GardenRepository;
import frentz.daniel.plants.repository.PlantRepository;
import frentz.daniel.plants.entity.PlantEntity;
import frentz.daniel.plants.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements PlantService{

    private PlantRepository plantRepository;
    private PlantConverterImpl plantConverter;
    private GardenRepository gardenRepository;

    public PlantServiceImpl(PlantRepository plantRepository,
                            PlantConverterImpl plantConverter,
                            GardenRepository gardenRepository){
        this.plantRepository = plantRepository;
        this.plantConverter = plantConverter;
        this.gardenRepository = gardenRepository;
    }

    @Override
    public PlantEntity create(Plant plant) {
        plant.setCreated(LocalDateTime.now());
        PlantEntity plantEntity = new PlantEntity();
        this.plantConverter.fillEntity(plantEntity, plant);
        return plantEntity;
    }

    @Override
    @Transactional
    public Plant getPlant(long id) {
        PlantEntity plantEntity = this.plantRepository.findById(id).orElseThrow(() -> new NotFoundException(Plant.class, id));
        Plant result = this.plantConverter.toModel(plantEntity);
        return result;
    }

    @Override
    @Transactional
    public List<Plant> getPlants() {
        List<PlantEntity> plants = this.plantRepository.findAll();
        return this.plantConverter.toModels(plants);
    }

    @Override
    public void removeGarden(long plantId) {
        PlantEntity plantEntity = this.plantRepository.getOne(plantId);
        this.plantRepository.save(plantEntity);
    }

    @Override
    public void updatePlant(Plant plant) {
        PlantEntity plantEntity = this.plantRepository.getOne(plant.getId());
        this.plantRepository.save(plantEntity);
    }

    @Override
    @Transactional
    public void deletePlant(long plantId) {
        if(this.plantRepository.existsById(plantId) == false){
            throw new NotFoundException(Plant.class, plantId);
        }
        this.plantRepository.deleteById(plantId);

    }

    @Override
    public void save(PlantEntity plantEntity) {
        this.plantRepository.save(plantEntity);
    }
}
