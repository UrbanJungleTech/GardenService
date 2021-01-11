package frentz.daniel.plants.service;

import frentz.daniel.plants.entity.PlantEntity;
import frentz.daniel.plants.model.Plant;

import java.util.List;

public interface PlantService {
    void createPlant(Plant plant);
    PlantEntity createAndSavePlant(Plant plant);
    Plant getPlant(long id);
    List<Plant> getPlants();
    void removeGarden(long plantId);
    void updatePlant(Plant plant);
    void deletePlant(long plantId);
}
