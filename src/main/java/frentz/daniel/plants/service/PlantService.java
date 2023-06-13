package frentz.daniel.plants.service;

import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.entity.PlantEntity;

import java.util.List;

public interface PlantService {
    PlantEntity create(Plant plant);
    Plant getPlant(long id);
    List<Plant> getPlants();
    void removeGarden(long plantId);
    void updatePlant(Plant plant);
    void deletePlant(long plantId);
    void save(PlantEntity plantEntity);
}
