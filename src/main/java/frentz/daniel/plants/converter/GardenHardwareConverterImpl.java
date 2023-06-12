package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.model.GardenHardware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GardenHardwareConverterImpl implements GardenHardwareConverter{

    private Map<String, SpecificGardenHardwareConverter> gardenHardwareConverters;

    public GardenHardwareConverterImpl(List<SpecificGardenHardwareConverter> specificGardenHardwareConverters){
        this.gardenHardwareConverters = new HashMap<>();
        for(SpecificGardenHardwareConverter specificGardenHardwareConverter : specificGardenHardwareConverters){
            this.gardenHardwareConverters.put(specificGardenHardwareConverter.getHardwareCategory(), specificGardenHardwareConverter);
        }
    }

    @Override
    public void fillHardware(GardenHardware gardenHardware, Hardware hardware) {
        hardware.setName(gardenHardware.getName());
        hardware.setPort(gardenHardware.getPort());
        hardware.setType(gardenHardware.getHardwareCategory());
    }

    @Override
    public void fillGardenHardware(GardenHardware gardenHardware, Hardware hardware){
        gardenHardware.setCurrentState(hardware.getCurrentState());
        gardenHardware.setDesiredState(hardware.getDesiredState());
        gardenHardware.setId(hardware.getId());
        gardenHardware.setName(hardware.getName());
        gardenHardware.setPort(hardware.getPort());
    }

    @Override
    public GardenHardware toModel(Hardware hardware) {
        GardenHardware result = this.gardenHardwareConverters.get(hardware.getType()).toModel(hardware);
        result.setHardwareCategory(hardware.getType());
        this.fillGardenHardware(result, hardware);
        return result;

    }

    @Override
    public Hardware toHardware(GardenHardware gardenHardware) {
        Hardware result = this.gardenHardwareConverters.get(gardenHardware.getHardwareCategory()).toHardware(gardenHardware);
        result.setType(gardenHardware.getHardwareCategory());
        fillHardware(gardenHardware, result);
        return result;
    }
}
