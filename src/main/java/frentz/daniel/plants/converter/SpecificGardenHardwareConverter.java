package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.model.GardenHardware;

import java.util.List;

public interface SpecificGardenHardwareConverter {
    String getHardwareCategory();
    GardenHardware toModel(Hardware hardware);
    List<GardenHardware> toModels(List<Hardware> hardwares);
    Hardware toHardware(GardenHardware light);

}
