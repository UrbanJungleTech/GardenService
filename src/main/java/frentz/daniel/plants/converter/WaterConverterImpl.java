package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Water;
import frentz.daniel.hardwareservice.client.model.Hardware;
import org.springframework.stereotype.Service;

@Service
public class WaterConverterImpl implements SpecificGardenHardwareConverter<Water>{

    @Override
    public String getHardwareCategory() {
        return "WATER";
    }

    @Override
    public Water toSpecificHardware(Hardware hardware) {
        Water result = new Water();
        result.setPressure(Long.valueOf(hardware.getMetadata().get("pressure")));
        result.setName(hardware.getName());
        result.setId(hardware.getId());
        return result;
    }


    @Override
    public Hardware toHardware(Water water) {
        Hardware result = new Hardware();
        result.getMetadata().put("pressure", String.valueOf(water.getPressure()));
        result.setType("WATER");
        result.setName(water.getName());
        result.setId(water.getId());
        return result;
    }
}
