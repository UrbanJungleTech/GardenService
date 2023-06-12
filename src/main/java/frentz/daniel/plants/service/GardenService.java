package frentz.daniel.plants.service;

import frentz.daniel.garden.model.*;
import frentz.daniel.hardwareservice.client.model.HardwareState;
import frentz.daniel.hardwareservice.client.model.Sensor;

import java.util.List;

public interface GardenService {
    void createGarden(Garden garden);
    Garden create(Garden garden);
    Garden getGarden(long id);
    List<Garden> getGardens();
    Garden addPlant(long gardenId, Plant plant);
    Garden removePlant(long gardenId, long plantId);
    void deleteGarden(long gardenId);
    Garden addLight(long gardenId, Light hardware);
    Garden addWater(long gardenId, Water water);
    Garden addHeater(long gardenId, Heater heater);
    Garden setHardwareState(long gardenId, long hardwareId, HardwareState hardwareState);
    double readAverageSensor(long gardenId,String sensorType);
    Garden addSensor(long gardenId, Sensor sensor);
}
