package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.GardenHardware;
import frentz.daniel.hardwareservice.client.model.Hardware;

public interface GardenHardwareConverter {
    void fillHardware(GardenHardware gardenHardware, Hardware hardware);
    void fillGardenHardware(GardenHardware gardenHardware, Hardware hardware);
    GardenHardware toModel(Hardware hardware);
    Hardware toHardware(GardenHardware gardenHardware);
}
