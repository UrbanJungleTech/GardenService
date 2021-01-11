package frentz.daniel.plants.service;

import frentz.daniel.plants.model.Garden;
import frentz.daniel.plants.model.Plant;

import java.util.List;

public interface GardenService {
    void createGarden(Garden garden);
    Garden createAndSaveGarden(Garden garden);
    Garden getGarden(long id);
    List<Garden> getGardens();
    void addPlant(long gardenId, Plant plant);
    Garden removePlant(long gardenId, long plantId);
    void deleteGarden(long gardenId);
}
