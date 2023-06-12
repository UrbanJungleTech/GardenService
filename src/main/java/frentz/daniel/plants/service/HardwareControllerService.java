package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.HardwareController;
import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.model.GardenHardwareController;
import frentz.daniel.model.Heater;
import frentz.daniel.model.Light;
import frentz.daniel.model.Water;

public interface HardwareControllerService {
    HardwareController createHardwareController(GardenHardwareController gardenHardwareController);
    void createLight(Long controllerId, Light light);
    void createWater(Long controllerId, Water water);
    void createHeater(Long controllerId, Heater heater);
    void createSensor(long controllerId, Sensor sensor);
}
