package frentz.daniel.plants.converter;

import frentz.daniel.controllerclient.model.Hardware;
import frentz.daniel.model.GardenHardware;

public interface GardenHardwareConverter {
    void fillHardware(GardenHardware gardenHardware, Hardware hardware);
    void fillGardenHardware(GardenHardware gardenHardware, Hardware hardware);
    GardenHardware toModel(Hardware hardware);
    Hardware toHardware(GardenHardware gardenHardware);
}
