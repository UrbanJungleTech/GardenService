package frentz.daniel.plants.service;

import frentz.daniel.model.Plant;
import frentz.daniel.plants.converter.PlantConverterImpl;
import frentz.daniel.plants.repository.PlantRepository;
import frentz.daniel.plants.entity.PlantEntity;
import frentz.daniel.plants.exception.NotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements PlantService{

    private PlantRepository plantRepository;
    private PlantConverterImpl plantConverter;

    public PlantServiceImpl(PlantRepository plantRepository, PlantConverterImpl plantConverter){
        this.plantRepository = plantRepository;
        this.plantConverter = plantConverter;
    }

    @Override
    public void createPlant(Plant plant) {
        plant.setCreated(LocalDateTime.now());
    }

    @Override
    public PlantEntity create(Plant plant) {
        this.createPlant(plant);
        PlantEntity plantEntity = new PlantEntity();
        plantEntity.setName(plant.getName());
        plantEntity.setSpecies(plant.getSpecies());
        plantEntity.setCreated(LocalDateTime.now());
        return plantEntity;
    }

    @Override
    public Plant getPlant(long id) {
        Optional<PlantEntity> plant = this.plantRepository.findById(id);
        if(plant.isEmpty() == true){
            throw new NotFoundException(Plant.class, id);
        }
        PlantEntity plantEntity = plant.get();
        Plant result = new Plant();
        result.setGardenId(plantEntity.getGarden().getId());
        result.setCreated(plantEntity.getCreated());
        result.setName(plantEntity.getName());
        return result;
    }

    @Override
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
    public void deletePlant(long plantId) {
        this.plantRepository.deleteById(plantId);
    }

    @Override
    public void save(PlantEntity plantEntity) {
        this.plantRepository.save(plantEntity);
    }
}
