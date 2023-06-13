package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.garden.model.*;
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
    public HardwareDetails toHardwareDetails(List<Hardware> hardwares) {
        HardwareDetails result = new HardwareDetails();
        for(Hardware hardware : hardwares){
            GardenHardware gardenHardware = this.gardenHardwareConverter.toModel(hardware);
            result.addHardware(gardenHardware);
        }
        return result;
    }

    @Override
    public List<Hardware> toHardware(HardwareDetails hardwareDetails) {
        List<Hardware> result = new ArrayList<>();
        for(GardenHardware gardenHardware : hardwareDetails.getHardware()){
            Hardware hardware = this.gardenHardwareConverter.toHardware(gardenHardware);
            result.add(hardware);
        }
        return result;
    }

}
