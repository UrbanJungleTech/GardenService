package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.HardwareController;
import frentz.daniel.garden.model.GardenHardwareController;

public interface GardenHardwareControllerConverter {
    GardenHardwareController toGardenHardwareController(long controllerId);
    HardwareController toHardwareController(GardenHardwareController gardenHardwareController);
}
