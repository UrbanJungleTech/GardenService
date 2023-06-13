package frentz.daniel.plants.service;

import frentz.daniel.garden.model.*;
import frentz.daniel.hardwareservice.client.model.HardwareState;
import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.hardwareservice.client.model.SensorReading;

import java.util.List;

public interface GardenService {
    Garden create(Garden garden);
    Garden getGarden(long id);
    List<Garden> getGardens();
    Plant addPlant(long gardenId, Plant plant);
    Garden removePlant(long gardenId, long plantId);
    void deleteGarden(long gardenId);
    GardenHardware addHardware(long gardenId, GardenHardware hardware);
    Garden setHardwareState(long gardenId, long hardwareId, HardwareState hardwareState);
    SensorReading readAverageSensor(long gardenId, String sensorType);
    GardenSensor addSensor(long gardenId, GardenSensor sensor);
}
