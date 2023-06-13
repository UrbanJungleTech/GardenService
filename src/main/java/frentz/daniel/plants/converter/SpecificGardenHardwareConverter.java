package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.garden.model.GardenHardware;

import java.util.List;

public interface SpecificGardenHardwareConverter<T extends GardenHardware> {
    String getHardwareCategory();
    T toSpecificHardware(Hardware hardware);
    Hardware toHardware(T hardware);

}
