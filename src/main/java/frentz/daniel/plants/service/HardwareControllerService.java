package frentz.daniel.plants.service;

import frentz.daniel.garden.model.*;
import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.hardwareservice.client.model.HardwareController;
import frentz.daniel.hardwareservice.client.model.Sensor;

public interface HardwareControllerService {
    HardwareController createHardwareController(GardenHardwareController gardenHardwareController);
    Hardware createHardware(long controllerId, GardenHardware gardenHardware);
    Sensor createSensor(long controllerId, Sensor sensor);
}
