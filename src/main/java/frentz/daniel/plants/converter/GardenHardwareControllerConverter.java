package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.HardwareController;
import frentz.daniel.model.GardenHardwareController;

public interface GardenHardwareControllerConverter {
    GardenHardwareController toModel(long controllerId);
    HardwareController toHardwareController(GardenHardwareController gardenHardwareController);
}
