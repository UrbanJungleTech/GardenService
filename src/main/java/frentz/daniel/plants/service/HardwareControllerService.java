package frentz.daniel.plants.service;

import frentz.daniel.hardwareservice.client.model.HardwareController;
import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.garden.model.GardenHardwareController;
import frentz.daniel.garden.model.Heater;
import frentz.daniel.garden.model.Light;
import frentz.daniel.garden.model.Water;

public interface HardwareControllerService {
    HardwareController createHardwareController(GardenHardwareController gardenHardwareController);
    void createLight(Long controllerId, Light light);
    void createWater(Long controllerId, Water water);
    void createHeater(Long controllerId, Heater heater);
    void createSensor(long controllerId, Sensor sensor);
}
