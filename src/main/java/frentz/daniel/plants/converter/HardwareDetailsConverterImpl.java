package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HardwareDetailsConverterImpl implements HardwareDetailsConverter{

    private GardenHardwareConverter gardenHardwareConverter;

    public HardwareDetailsConverterImpl(GardenHardwareConverter gardenHardwareConverter){
        this.gardenHardwareConverter = gardenHardwareConverter;
    }

    @Override
    public HardwareDetails toModel(List<Hardware> hardwares) {

        HardwareDetails result = new HardwareDetails();

        for(Hardware hardware : hardwares){
            GardenHardware gardenHardware = this.gardenHardwareConverter.toModel(hardware);
            switch(hardware.getType()){
                case "LIGHT":
                    result.addLight((Light)gardenHardware);
                    break;
                case "WATER":
                    result.addWater((Water)gardenHardware);
                    break;
                case "HEATER":
                    result.addHeater((Heater)gardenHardware);
                    break;
            }
        }
        return result;
    }

    @Override
    public List<Hardware> toHardware(HardwareDetails hardwareDetails) {
        List<Hardware> result = new ArrayList<>();
        for(Light light : hardwareDetails.getLights()){
            Hardware hardware = this.gardenHardwareConverter.toHardware(light);
            result.add(hardware);
        }
        for(Heater heater : hardwareDetails.getHeaters()){
            Hardware hardware = this.gardenHardwareConverter.toHardware(heater);
            result.add(hardware);
        }
        for(Water water : hardwareDetails.getWaters()){
            Hardware hardware = this.gardenHardwareConverter.toHardware(water);
            result.add(hardware);
        }
        return result;
    }

}
