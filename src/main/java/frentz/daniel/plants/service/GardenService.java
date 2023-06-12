package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.HardwareState;
import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.controllerclient.model.SensorType;
import frentz.daniel.model.*;

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
    double readAverageSensor(long gardenId, SensorType sensorType);
    Garden addSensor(long gardenId, Sensor sensor);
}
